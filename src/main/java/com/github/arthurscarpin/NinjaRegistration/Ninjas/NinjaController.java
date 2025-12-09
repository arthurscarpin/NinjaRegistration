package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

    // Welcome route
    @GetMapping("/welcome")
    public String welcome() {
        return "This is my first message on this route.";
    }

    // To add ninja (CREATE)
    @PostMapping("/create")
    public String createNinja() {
        return "Ninja created!";
    }

    // To find all ninjas (READ)
    @GetMapping("/all")
    public String getAllNinjas() {
        return "List of all ninjas.";
    }

    // To find ninja by id (READ)
    @GetMapping("/allID")
    public String getAllNinjasById() {
        return "List of all ninjas by ID.";
    }

    // To update ninja (UPDATE)
    @PutMapping("/alterID")
    public String updateNinjaById() {
        return "Ninja updated!";
    }

    // To delete ninja (DELETE)
    @DeleteMapping("/deleteID")
    public String deleteNinjaById() {
        return "Ninja deleted!";
    }
}