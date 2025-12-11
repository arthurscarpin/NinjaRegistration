package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninja")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // Welcome route
    @GetMapping("/welcome")
    public String welcome() {
        return "This is my first message on this route.";
    }

    // To add ninja (CREATE)
    @PostMapping("/create")
    public NinjaDTO createNinja(@RequestBody NinjaDTO ninja) {
        return ninjaService.create(ninja);
    }

    // To find all ninjas (READ)
    @GetMapping("/list")
    public List<NinjaModel> listAllNinjas() {
        return ninjaService.listAll();
    }

    // To find ninja by id (READ)
    @GetMapping("/list/{id}")
    public NinjaModel listNinjaById(@PathVariable Long id) {
        return ninjaService.listById(id);
    }

    // To update ninja (UPDATE)
    @PutMapping("/alter/{id}")
    public NinjaModel updateNinjaById(@PathVariable Long id, @RequestBody NinjaModel ninjaUpdated) {
        return ninjaService.updateById(id, ninjaUpdated);
    }

    // To delete ninja (DELETE)
    @DeleteMapping("/delete/{id}")
    public void deleteNinjaById(@PathVariable Long id) {
        ninjaService.deleteById(id);
    }
}