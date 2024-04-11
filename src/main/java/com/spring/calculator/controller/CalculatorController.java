package com.spring.calculator.controller;

import com.spring.calculator.model.Number;
import com.spring.calculator.service.NumberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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
    @Autowired
    @Qualifier("NumberService")
    public NumberService numberService;

    // Maršrutizavimo informacija. Šiuo atveju ji nurodo Spring karkasui,
    // kad visos HTTP užklausos, kurių kelias yra "/", bus apdorotos metodo home().
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home(Model model) {
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
            // String calculate(int number1, int number2, String operation, ModelMap modelMap) {
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

            numberService.save(new Number(number1, number2, operation, result));

            // prefiksas + jsp failo pavadinimas + sufiksas
            return "calculate";
        }
    }
    @GetMapping("/numbers")
    public String getAllNumbers(Model model) {
        model.addAttribute("numbers", numberService.getAll());
        return "numbers";
    }
    @GetMapping("/view{id}")
    public String getById(@RequestParam("id") int id, Model model) {
        model.addAttribute("number", numberService.getById(id));
        return "number";
    }
    @GetMapping("/delete{id}")
    public String delete(@RequestParam("id") int id, Model model) {
        numberService.delete(id);
        model.addAttribute("numbers", numberService.getAll());
        return "numbers";
    }
    @GetMapping("/refresh{id}")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("number", numberService.getById(id));
        return "refresh";
    }
    @PostMapping("/refreshNumber")
    public String updateNumber(@ModelAttribute("number") Number number) {
        numberService.update(number);
        return "redirect:/view?id=" + number.getId();
    }
}
