package com.github.arthurscarpin.NinjaRegistration.Missions;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mission")
public class MissionController {

    // To create mission (CREATE)
    @PostMapping("/create")
    public String createMission() {
        return "Mission created!";
    }

    // To find all missions (READ)
    @GetMapping("/listAll")
    public String listAllMissions() {
        return "List of all missions.";
    }

    // To find mission by id (READ)
    @GetMapping("/listID")
    public String listMissionById() {
        return "List of all missions by ID.";
    }

    // To update mission (UPDATE)
    @PutMapping("/alterID")
    public String updateMissionById() {
        return "Mission updated!";
    }

    // To delete mission (DELETE)
    @DeleteMapping("/deleteID")
    public String deleteMissionById() {
        return "Mission deleted!";
    }
}
