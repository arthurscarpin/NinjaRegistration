package com.github.arthurscarpin.NinjaRegistration.Missions;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissionService {

    private final MissionRepository missionRepository;

    private final MissionMapper missionMapper;

    public MissionService(MissionRepository missionRepository, MissionMapper missionMapper) {
        this.missionRepository = missionRepository;
        this.missionMapper = missionMapper;
    }

    // Create new mission
    public MissionDTO create(MissionDTO missionDTO) {
        MissionModel mission = missionMapper.map(missionDTO);
        mission = missionRepository.save(mission);
        return missionMapper.map(mission);
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
    public MissionModel updateById(Long id, MissionModel missionUpdated) {
        if (missionRepository.existsById(id)) {
            missionUpdated.setId(id);
            return missionRepository.save(missionUpdated);
        } else {
            return null;
        }
    }

    // Delete mission by ID - It has to return void
    public void deleteById(Long id) {
        missionRepository.deleteById(id);
    }
}
