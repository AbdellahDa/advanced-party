package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;

@Controller
public class VenueController {
    private final Logger logger = LoggerFactory.getLogger(VenueController.class);
    @Autowired
    private VenueRepository venueRepository;

    @GetMapping({"/venuedetails", "/venuedetails/{id}"})
    public String venueDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";
        Optional<Venue> optionalVenue = venueRepository.findById(id);
        optionalVenue.ifPresent(venue -> model.addAttribute("venue", venue));
        return "venuedetails";
    }

    @GetMapping("/venuelist/outdoor/yes")
    public String venueListOutdoorYes(Model model) {
        Iterable<Venue> venues = venueRepository.findByOutdoor(true);
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping("/venuelist/outdoor/no")
    public String venueListOutdoorNo(Model model) {
        Iterable<Venue> venues = venueRepository.findByOutdoor(false);
        model.addAttribute("venues", venues);
        return "venuelist";
    }

    @GetMapping({"/venuelist", "/venuelist/{something}"})
    public String venueListSomething(Model model) {
        Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("nrVenues", venueRepository.count());
        model.addAttribute("venues", allVenues);
        return "venuelist";
    }

    @GetMapping({"/venuelist/filter"})
    public String venueListWithFilter(Model model,
                                      @RequestParam(required = false) Integer minimumCapacity,
                                      @RequestParam(required = false) Integer maximumCapacity) {
        logger.info(String.format("venueListWithFilter -- min=%d", minimumCapacity));
        Iterable<Venue> allVenues;
        if (minimumCapacity != null && maximumCapacity != null) {
            allVenues = venueRepository.findByCapacityIsBetween(minimumCapacity, maximumCapacity);
        } else if (minimumCapacity != null) {
            allVenues = venueRepository.findByCapacityIsGreaterThanEqual(minimumCapacity);
        } else if (maximumCapacity != null) {
            allVenues = venueRepository.findByCapacityIsGreaterThan(maximumCapacity);
        } else {
            allVenues = venueRepository.findAll();
        }
        model.addAttribute("venues", allVenues);
        int nrVenues = ((Collection<Venue>) allVenues).size();
        model.addAttribute("nrVenues", nrVenues);

        model.addAttribute("showFilter", true);
        model.addAttribute("mincapacity", minimumCapacity);
        return "venuelist";
    }

}
