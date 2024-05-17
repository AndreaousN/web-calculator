package com.spring.calculator.controller;

import com.spring.calculator.model.User;
import com.spring.calculator.service.SecurityService;
import com.spring.calculator.service.UserService;
import com.spring.calculator.utils.UserValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@EnableAutoConfiguration
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("UserService") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@ModelAttribute("user") User user, BindingResult result) {
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return "register";
        }

        // Save the user to the database
        userService.createUser(user);

        securityService.autoLogin(user.getUsername(), user.getPassword());

        return "redirect:/login";
    }

    @GetMapping(value = "/login")
    public String showLoginForm(Model model, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute( "user", new User());
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@ModelAttribute("user") User loginUser, BindingResult result) {
        // Retrieve the user from the database based on the provided username

        userValidator.validate(loginUser, result);

        if (result.hasErrors()) {
            return "login";
        }

        return "redirect:/calculator";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
