package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninja")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // To add ninja (CREATE)
    @PostMapping("/create")
    public ResponseEntity<String> createNinja(@RequestBody NinjaDTO ninjaDTO) {
        NinjaDTO ninja = ninjaService.create(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja created successfully: " + ninja.getName() + " (ID: " + ninja.getId() + ")");
    }

    // To find all ninjas (READ)
    @GetMapping("/list")
    public ResponseEntity<List<NinjaDTO>> listAllNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listAll();
        return ResponseEntity.ok(ninjas);
    }

    // To find ninja by id (READ)
    @GetMapping("/list/{id}")
    public ResponseEntity<?> listNinjaById(@PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listById(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja with ID " + id + " not found.");
        }
    }

    // To update ninja (UPDATE)
    @PutMapping("/alter/{id}")
    public ResponseEntity<?> updateNinjaById(@PathVariable Long id, @RequestBody NinjaDTO ninjaDTO) {
        if (ninjaService.listById(id) != null) {
            NinjaDTO ninja = ninjaService.updateById(id, ninjaDTO);
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja with ID " + id + " not found.");
        }
    }

    // To delete ninja (DELETE)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNinjaById(@PathVariable Long id) {
        if (ninjaService.listById(id) != null) {
            ninjaService.deleteById(id);
            return ResponseEntity.ok("Ninja with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja with ID " + id + " not found.");
        }
    }
}