package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjaService {

    private final NinjaRepository ninjaRepository;

    private final NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    // Create new ninja
    public NinjaDTO create(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
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
    public NinjaModel updateById(Long id, NinjaModel ninjaUpdated) {
        if (ninjaRepository.existsById(id)) {
            ninjaUpdated.setId(id);
            return ninjaRepository.save(ninjaUpdated);
        } else {
            return null;
        }
    }

    // Delete ninja by ID - It has to return void
    public void deleteById(Long id) {
        ninjaRepository.deleteById(id);
    }
}
