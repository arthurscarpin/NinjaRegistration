package com.github.arthurscarpin.NinjaRegistration.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<NinjaDTO> listAll() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();
        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    // List ninja by ID
    public NinjaDTO listById(Long id) {
        Optional<NinjaModel> ninja = ninjaRepository.findById(id);
        return ninja.map(ninjaMapper::map)
                .orElse(null);
    }

    //Update ninja by ID
    public NinjaDTO updateById(Long id, NinjaDTO ninjaDTO) {
        Optional<NinjaModel> ninjaExisting = ninjaRepository.findById(id);
        if (ninjaExisting.isPresent()) {
            NinjaModel ninjaToUpdate = ninjaMapper.map(ninjaDTO);
            ninjaToUpdate.setId(id);
            ninjaToUpdate = ninjaRepository.save(ninjaToUpdate);
            return ninjaMapper.map(ninjaToUpdate);
        }
        return null;
    }

    // Delete ninja by ID - It has to return void
    public void deleteById(Long id) {
        ninjaRepository.deleteById(id);
    }
}
