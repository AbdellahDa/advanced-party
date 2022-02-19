package be.thomasmore.party.controllers;

import be.thomasmore.party.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    public String generateClientCode() {
        String firstName = "Pe";
        String lastName  = "s";
        String result = firstName + lastName;
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        String firstName = "Pe";
        String lastName  = "s";
        String result = firstName + lastName;
        System.out.println(result);
    }
}
