package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }

    // Create new ninja
    public NinjaModel create(NinjaModel ninja) {
        return ninjaRepository.save(ninja);
    }

    // List all ninjas
    public List<NinjaModel> listAll() {
        return ninjaRepository.findAll();
    }

    // List ninja by ID
    public NinjaModel listById(Long id) {
        Optional<NinjaModel> ninjaById = ninjaRepository.findById(id);
        return ninjaById.orElse(null);
    }

    //Update ninja by ID

    // Delete ninja by ID - It has to return void
    public void deleteById(Long id) {
        ninjaRepository.deleteById(id);
    }
}
