package com.github.arthurscarpin.NinjaRegistration.Missions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createMission(@RequestBody MissionDTO missionDTO) {
        MissionDTO mission = missionService.create(missionDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Mission created successfully: " + mission.getName() + " (ID: " + mission.getId() + ")");
    }

    // To find all missions (READ)
    @GetMapping("/list")
    public ResponseEntity<List<MissionDTO>> listAllMissions() {
        List<MissionDTO> missions = missionService.listAll();
        return ResponseEntity.ok(missions);
    }

    // To find mission by id (READ)
    @GetMapping("/list/{id}")
    public ResponseEntity<?> listMissionById(@PathVariable Long id) {
        MissionDTO mission = missionService.listById(id);
        if (mission != null) {
            return ResponseEntity.ok(mission);
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Mission with ID " + id + " not found.");
        }
    }

    // To update mission (UPDATE)
    @PutMapping("/alter/{id}")
    public ResponseEntity<?> updateMissionById(@PathVariable Long id, @RequestBody MissionDTO missionDTO) {
        if (missionService.listById(id) != null) {
            MissionDTO mission = missionService.updateById(id, missionDTO);
            return ResponseEntity.ok(mission);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Mission with ID " + id + " not found.");
        }
    }

    // To delete mission (DELETE)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMissionById(@PathVariable Long id) {
        if (missionService.listById(id) != null) {
            missionService.deleteById(id);
            return ResponseEntity.ok("Mission with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Mission with ID " + id + " not found.");
        }
    }
}
