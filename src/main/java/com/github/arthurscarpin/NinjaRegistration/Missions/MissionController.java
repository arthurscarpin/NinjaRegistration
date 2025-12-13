package com.github.arthurscarpin.NinjaRegistration.Missions;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create a new mission", description = "Creates a new mission with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Mission created successfully",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Mission Created Example",
                                    description = "Example of a successful mission creation response",
                                    value = "Mission created successfully: Rescue Operation (ID: 1)"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Invalid Input Example",
                                    description = "Example of an invalid input response",
                                    value = "Invalid input data provided for mission creation."
                            )
                    )
            )
    })
    public ResponseEntity<String> createMission(
            @Parameter(description = "Details of the mission to be created", required = true)
            @RequestBody MissionDTO missionDTO) {
        MissionDTO mission = missionService.create(missionDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Mission created successfully: " + mission.getName() + " (ID: " + mission.getId() + ")");
    }

    // To find all missions (READ)
    @GetMapping("/list")
    @Operation(summary = "List all missions", description = "Retrieves a list of all registered missions.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of missions retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Missions List Example",
                                    description = "Example of a successful missions list response",
                                    value = "[{\"id\":1,\"name\":\"Rescue Operation\",\"rank\":\"S\"},{\"id\":2,\"name\":\"Stealth Recon\",\"rank\":\"D.\"}]"
                            )
                    )
            )
    })
    public ResponseEntity<List<MissionDTO>> listAllMissions() {
        List<MissionDTO> missions = missionService.listAll();
        return ResponseEntity.ok(missions);
    }

    // To find mission by id (READ)
    @GetMapping("/list/{id}")
    @Operation(summary = "Get mission by ID", description = "Retrieves a mission by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Mission retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Missions List Example",
                                    description = "Example of a successful mission retrieval response",
                                    value = "{\"id\":1,\"name\":\"Rescue Operation\",\"rank\":\"S\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Mission not found",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Mission Not Found Example",
                                    description = "Example of a mission not found response",
                                    value = "Mission with ID 1 not found."
                            )
                    )
            )
    })
    public ResponseEntity<?> listMissionById(
            @Parameter(description = "ID of the mission to be retrieved", required = true)
            @PathVariable Long id) {
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
    @Operation(summary = "Update mission by ID", description = "Updates the details of an existing mission by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Mission updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Mission Updated Example",
                                    description = "Example of a successful mission update response",
                                    value = "{\"id\":1,\"name\":\"Rescue Operation Updated\",\"rank\":\"S\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Mission not found",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Mission Not Found Example",
                                    description = "Example of a mission not found response",
                                    value = "Mission with ID 1 not found."
                            )
                    )
            )
    })
    public ResponseEntity<?> updateMissionById(
            @Parameter(description = "ID of the mission to be updated", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated mission details", required = true)
            @RequestBody MissionDTO missionDTO) {
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
    @Operation(summary = "Delete mission by ID", description = "Deletes an existing mission by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Mission deleted successfully",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Mission Deleted Example",
                                    description = "Example of a successful mission deletion response",
                                    value = "Mission with ID 1 deleted successfully."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Mission not found",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Mission Not Found Example",
                                    description = "Example of a mission not found response",
                                    value = "Mission with ID 1 not found."
                            )
                    )
            )
    })
    public ResponseEntity<String> deleteMissionById(
            @Parameter(description = "ID of the mission to be deleted", required = true)
            @PathVariable Long id) {
        if (missionService.listById(id) != null) {
            missionService.deleteById(id);
            return ResponseEntity.ok("Mission with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Mission with ID " + id + " not found.");
        }
    }
}
