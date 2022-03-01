package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;
import be.thomasmore.party.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/artistlist")
    public String artistList(Model model) {
        Iterable<Artist> allArtists = artistRepository.findAll();
        model.addAttribute("artists", allArtists);
        return "artistlist";
    }

    @GetMapping({"/artistdetails", "/artistdetails/{id}"})
    public String artistdetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "artistdetails";
        Optional<Artist> optionalArtist = artistRepository.findById(id);
        if (optionalArtist.isPresent()) {
            model.addAttribute("artist", optionalArtist.get());
        }
        return "artistdetails";
    }

    @GetMapping({"/artistlist/filter"})
    public String artistListWithFilter(Model model, @RequestParam(required = false) String keyword) {
        Iterable<Artist> allArtist = artistRepository.findAll();
        if (keyword != null) {

            allArtist = artistRepository.findByKeyword(keyword);
        } else {
            allArtist = artistRepository.findAll();

        }
        model.addAttribute("artists", allArtist);
        model.addAttribute("nrArtists", artistRepository.count());
        model.addAttribute("showFilter", true);
        model.addAttribute("keyword", keyword);
        return "artistlist";
    }
}
