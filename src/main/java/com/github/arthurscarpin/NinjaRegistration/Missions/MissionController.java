package com.github.arthurscarpin.NinjaRegistration.Missions;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    // To create mission (CREATE)
    @PostMapping("/create")
    public MissionDTO createMission(@RequestBody MissionDTO mission) {
        return missionService.create(mission);
    }

    // To find all missions (READ)
    @GetMapping("/list")
    public List<MissionDTO> listAllMissions() {
        return missionService.listAll();
    }

    // To find mission by id (READ)
    @GetMapping("/list/{id}")
    public MissionDTO listMissionById(@PathVariable Long id) {
        return missionService.listById(id);
    }

    // To update mission (UPDATE)
    @PutMapping("/alter/{id}")
    public MissionDTO updateMissionById(@PathVariable Long id, @RequestBody MissionDTO missionUpdated) {
        return missionService.updateById(id, missionUpdated);
    }

    // To delete mission (DELETE)
    @DeleteMapping("/delete/{id}")
    public void deleteMissionById(@PathVariable Long id) {
        missionService.deleteById(id);
    }
}
