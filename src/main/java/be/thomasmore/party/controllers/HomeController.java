package be.thomasmore.party.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        int myCalculatedValue = 34 * 62;
        model.addAttribute("myCalculatedValue", myCalculatedValue);
        model.addAttribute("appName","MyApp");
        return "home";
    }
    @GetMapping("/about")
    public String about(Model model) {
        String myName = "Abdellah";
        model.addAttribute("myName", myName);
        String myCity = "Borgerhout";
        model.addAttribute("myCity", myCity);
        String myStreet = "Appelstraat 47";
        model.addAttribute("myStreet", myStreet);
        return "about";
    }
}
