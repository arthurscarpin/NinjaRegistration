package com.github.arthurscarpin.NinjaRegistration.Missions;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<MissionDTO> listAll() {
        List<MissionModel> mission = missionRepository.findAll();
        return mission.stream()
                .map(missionMapper::map)
                .collect(Collectors.toList());
    }

    // List mission by ID
    public MissionDTO listById(Long id) {
        Optional<MissionModel> mission = missionRepository.findById(id);
        return mission.map(missionMapper::map)
                .orElse(null);
    }

    //Update mission by ID
    public MissionDTO updateById(Long id, MissionDTO missionDTO) {
        Optional<MissionModel> missionExisting = missionRepository.findById(id);
        if (missionExisting.isPresent()) {
            MissionModel missionToUpdate = missionMapper.map(missionDTO);
            missionToUpdate.setId(id);
            missionToUpdate = missionRepository.save(missionToUpdate);
            return missionMapper.map(missionToUpdate);
        }
        return null;
    }

    // Delete mission by ID - It has to return void
    public void deleteById(Long id) {
        missionRepository.deleteById(id);
    }
}
