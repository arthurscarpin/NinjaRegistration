package com.github.arthurscarpin.NinjaRegistration.Missions;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    // Create new mission
    public MissionModel create(MissionModel mission) {
        return missionRepository.save(mission);
    }

    // List all missions
    public List<MissionModel> listAll() {
        return missionRepository.findAll();
    }

    // List mission by ID
    public MissionModel listById(Long id) {
        Optional<MissionModel> mission = missionRepository.findById(id);
        return mission.orElse(null);
    }

    //Update mission by ID

    // Delete mission by ID - It has to return void
    public void deleteById(Long id) {
        missionRepository.deleteById(id);
    }
}
