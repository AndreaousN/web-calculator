package com.spring.calculator.controller;

import com.spring.calculator.model.User;
import com.spring.calculator.service.UserService;
import com.spring.calculator.utils.BCryptPassword;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@EnableAutoConfiguration
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
    public String registerNewUser(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        // Check if the password is valid
        if (!isValidPassword(user.getPassword())) {
            result.rejectValue("password", "error.user", "Your password must be at least 8 characters long and contain at least one letter, one number, and one special character among @$!%*#?&.");
            return "register";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            result.rejectValue("passwordConfirm", "error.user", "Passwords do not match");
            return "register";
        }
        // Check if the username already exists
        if (userService.getUserByUsername(user.getUsername()) != null) {
            result.rejectValue("username", "error.user", "Username already exists");
            return "register";
        }
        // Check if the email already exists
        if (userService.getUserByEmail(user.getEmail()) != null) {
            result.rejectValue("email", "error.user", "Email already exists");
            return "register";
        }
        // Save the user to the database
        userService.createUser(user);
        return "redirect:/login";
    }

    private boolean isValidPassword(String password) {
        return password != null && password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    @GetMapping(value = "/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/loginUser")
    public String loginUser(@ModelAttribute("user") User loginUser, HttpSession session, Model model) {
        // Retrieve the user from the database based on the provided username
        User userFromDB = userService.getUserByUsername(loginUser.getUsername());

        // Check if the user exists
        if (userFromDB == null || !BCryptPassword.checkPassword(loginUser.getPassword(), userFromDB.getPassword())) {
            model.addAttribute("errorMessage", "Username or password incorrect");
            return "login";
        }

        // Log the entered username and hashed password for debugging purposes
        logger.info("Entered Username: {}", loginUser.getUsername());
        logger.info("Entered Password (Hashed): {}", BCryptPassword.hashPassword(loginUser.getPassword()));

        // Compare the hashed passwords using the checkPassword method
        if (BCryptPassword.checkPassword(loginUser.getPassword(), userFromDB.getPassword())) {
            // Passwords match, user is authenticated
            session.setAttribute("username", userFromDB.getUsername());
            return "redirect:/calculator"; // Redirect to the user's profile page
        } else {

            return "redirect:/login";
        }
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
