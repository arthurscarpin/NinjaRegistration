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
    public NinjaDTO createNinja(@RequestBody NinjaDTO ninjaDTO) {
        return ninjaService.create(ninjaDTO);
    }

    // To find all ninjas (READ)
    @GetMapping("/list")
    public List<NinjaDTO> listAllNinjas() {
        return ninjaService.listAll();
    }

    // To find ninja by id (READ)
    @GetMapping("/list/{id}")
    public NinjaDTO listNinjaById(@PathVariable Long id) {
        return ninjaService.listById(id);
    }

    // To update ninja (UPDATE)
    @PutMapping("/alter/{id}")
    public NinjaDTO updateNinjaById(@PathVariable Long id, @RequestBody NinjaDTO ninjaDTO) {
        return ninjaService.updateById(id, ninjaDTO);
    }

    // To delete ninja (DELETE)
    @DeleteMapping("/delete/{id}")
    public void deleteNinjaById(@PathVariable Long id) {
        ninjaService.deleteById(id);
    }
}