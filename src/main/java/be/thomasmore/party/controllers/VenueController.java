package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VenueController {

    @GetMapping("/venuedetails")
    public String venuedetails(Model model) {
        Venue v = new Venue("Boesjkammeree", "https://denaalmoezenier.weebly.com/", 100, true, true, false, false, "Antwerpen", 1);
        model.addAttribute("venue", v);
        return "venuedetails";
    }


}
