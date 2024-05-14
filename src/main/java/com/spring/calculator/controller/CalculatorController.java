package com.spring.calculator.controller;

import com.spring.calculator.model.Number;
import com.spring.calculator.model.User;
import com.spring.calculator.repository.UserRepository;
import com.spring.calculator.service.NumberService;
import com.spring.calculator.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// Web kontroleris leidžia viduje naudoti @RequestMapping.
// @RestController anotacija nurodo , jog pvz: String tipo rezultatas turi būti išspausdintas klientui toks koks yra

//@RestController grąžina ne vaizdą
//Kadangi mums reikia grąžinti vaizdą naudojant Spring MVC, naudojame @Controller

@Controller
// Žymi konfigūracijos komponentą viduje, leidžia kurti bean'us per metodus su @Bean anotacija.
// Ši klasės lygio anotacija nurodo Spring karkasui, kad turėtų "atpažinti" konfigūraciją.
// Remiasi priklausomybėmis (JAR bibliotekomis), kurias programuotojas įtraukia į projektą (pom.xml)
// šiuo atveju ji veikia kartu su main metodu.

@EnableAutoConfiguration
public class CalculatorController {
    private final NumberService numberService;
    private final UserService userService;
    @Autowired
    public CalculatorController(@Qualifier("NumberService") NumberService numberService,
                                @Qualifier("UserService")UserService userService) {
        this.numberService = numberService;
        this.userService = userService;
    }


    // Maršrutizavimo informacija. Šiuo atveju ji nurodo Spring karkasui,
    // kad visos HTTP užklausos, kurių kelias yra "/", bus apdorotos metodo home().
    @RequestMapping(method = RequestMethod.GET, value = "/calculator")
    public String calculator(Model model) {
        // Jeigu modelis "number" nepavyksta praeiti validacijos - per jį grąžinamos validacijos klaidos į vaizdą (view).
        model.addAttribute("number", new Number());
        // Grąžiname JSP failą, jis turi būti talpinamas 'webapp -> WEB-INF -> jsp' kataloge
        return "calculator";
    }

    // Kadangi skaičiuotuvo forma naudoja POST metodą, čia taip pat nurodome POST.
    // @RequestMapping(method = RequestMethod.POST, value = "/calculate")

    @PostMapping("/calculate")
        // Naudotis @RequestParam reikia, kai raktai skiriasi nuo frontendo ir backendo
        // Jeigu daroma validacija, tai pirmas parametras eina su @Valid anotacija, o antras - @BindigResult
    String calculate(@Valid @ModelAttribute("number") Number number, BindingResult br,
                     @RequestParam HashMap<String, String> numbers, ModelMap modelMap) {
        if (br.hasErrors()) {
            // Jeigu pagaunama valdicajos klaida, vartotojas turi likti pradiniame lange
            return "calculator";
        } else {
            int number1 = Integer.parseInt(numbers.get("number1"));
            int number2 = Integer.parseInt(numbers.get("number2"));
            String operation = numbers.get("operation");

            System.out.println("Results: " + numbers.entrySet());
            // String calculate(int number1, int number2, String operation, ModelMap) {
            int result = switch (operation) {
                case "+" -> number1 + number2;
                case "-" -> number1 - number2;
                case "*" -> number1 * number2;
                case "/" -> number1 / number2;
                default -> 0;
            };

            // ModelMap objektas naudojamas siųsti reikšmes iš Spring MVC kontrolerio į JSP
            modelMap.put("number1", number1);
            modelMap.put("number2", number2);
            modelMap.put("operation", operation);
            modelMap.put("result", result);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.getUserByUsername(username);
            numberService.save(new Number(number1, number2, operation, result, user));

            // prefiksas + jsp failo pavadinimas + sufiksas
            return "calculate";
        }
    }

    @GetMapping(value = "/allNumbers")
    public String allNumbers(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);

        model.addAttribute("numbers", numberService.getUserOperationsById(user.getId()));

        return "allNumbers";
    }

    @GetMapping(value = "/showNum")
    public String showNum(int id, Model model) {
        System.out.println(id);
        model.addAttribute("number", numberService.getById(id));
        System.out.println(numberService.getById(id));
        return "number";
    }

    @GetMapping(value = "/delete")
    public String delete(int id, Model model) {
        model.addAttribute("number", numberService.getAll());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));

        if (!isAdmin) {
            return "403";
        }

        numberService.delete(id);

        return "redirect:/allNumbers";
    }

    @GetMapping(value = "/updateNumber")
    public String update(int id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"));

        if (!isAdmin) {
            return "403";
        }

        model.addAttribute("number", numberService.getById(id));

        return "updateNumber";
    }

    @PostMapping(value = "/updateNum")
    public String updateNum(@ModelAttribute("number") Number number) {
        numberService.update(number);
        return "redirect:/showNum?id=" + number.getId();
    }
}
