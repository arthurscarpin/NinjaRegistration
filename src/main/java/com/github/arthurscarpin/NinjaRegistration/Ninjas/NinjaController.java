package com.github.arthurscarpin.NinjaRegistration.Ninjas;

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
@RequestMapping("/ninja")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    // To add ninja (CREATE)
    @PostMapping("/create")
    @Operation(summary = "Create a new ninja", description = "Creates a new ninja with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Ninja created successfully",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Successful Creation",
                                    description = "Example of a successful ninja creation response",
                                    value = "Ninja created successfully: Naruto Uzumaki (ID: 1)"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                        mediaType = "text/plain",
                        examples = @ExampleObject(
                                name = "Invalid Input",
                                description = "Example of an invalid input data response",
                                value = "Invalid input data: Name cannot be empty."
                        )
                )
            )
    })
    public ResponseEntity<String> createNinja(
            @Parameter(description = "Details of the ninja to be created", required = true)
            @RequestBody NinjaDTO ninjaDTO) {
        NinjaDTO ninja = ninjaService.create(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja created successfully: " + ninja.getName() + " (ID: " + ninja.getId() + ")");
    }

    // To find all ninjas (READ)
    @GetMapping("/list")
    @Operation(summary = "List all ninjas", description = "Retrieves a list of all registered ninjas.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of ninjas retrieved successfully",
                    content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(
                                name = "Ninja List",
                                description = "Example of a successful ninja list retrieval response",
                                value = "[{\"id\":1,\"name\":\"Naruto Uzumaki\",\"email\":\"naruto@email.com\",\"imageUrl\":\"https://preview.redd.it/which-episode-did-naruto-put-his-hokage-hat-on-v0-4o5fenjwmbyb1.jpg?width=640&crop=smart&auto=webp&s=7d10ede18e34f5794729903d7887f26d7b04fac9\",\"age\":33,\"missions\":{\"id\":23,\"name\":\"Fourth Great Ninja War\",\"rank\":\"S\"},\"rank\":\"KAGE\"},{\"id\":2,\"name\":\"Sasuke Uchiha\",\"email\":\"sasuke@email.com\",\"imageUrl\":\"https://preview.redd.it/is-sasuke-dead-in-boruto-v0-m3owsv6ptqhe1.jpeg?width=640&crop=smart&auto=webp&s=6ae446069b4b97f2e4cf8b0b58361a24b116dd04\",\"age\":33,\"missions\":{\"id\":20,\"name\":\"Search for Itachi Uchiha\",\"rank\":\"A\"},\"rank\":\"ANBU\"}]"
                        )
                )
            )
    })
    public ResponseEntity<List<NinjaDTO>> listAllNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listAll();
        return ResponseEntity.ok(ninjas);
    }

    // To find ninja by id (READ)
    @GetMapping("/list/{id}")
    @Operation(summary = "Get ninja by ID", description = "Retrieves a ninja by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ninja retrieved successfully",
                    content = @Content(
                        mediaType = "application/json",
                        examples = @ExampleObject(
                                name = "Ninja Details",
                                description = "Example of a successful ninja retrieval response",
                                value = "{\"id\":1,\"name\":\"Naruto Uzumaki\",\"email\":\"naruto@email.com\",\"imageUrl\":\"https://preview.redd.it/which-episode-did-naruto-put-his-hokage-hat-on-v0-4o5fenjwmbyb1.jpg?width=640&crop=smart&auto=webp&s=7d10ede18e34f5794729903d7887f26d7b04fac9\",\"age\":33,\"missions\":{\"id\":23,\"name\":\"Fourth Great Ninja War\",\"rank\":\"S\"},\"rank\":\"KAGE\"}"
                        )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ninja not found",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Ninja Not Found",
                                    description = "Example of a ninja not found response",
                                    value = "Ninja with ID 1 not found."
                            )
                    )
            )
    })
    public ResponseEntity<?> listNinjaById(
            @Parameter(description = "ID of the ninja to be retrieved", required = true)
            @PathVariable Long id) {
        NinjaDTO ninja = ninjaService.listById(id);
        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja with ID " + id + " not found.");
        }
    }

    // To update ninja (UPDATE)
    @PutMapping("/alter/{id}")
    @Operation(summary = "Update ninja by ID", description = "Updates the details of an existing ninja by its ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ninja updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Updated Ninja Details",
                                    description = "Example of a successful ninja update response",
                                    value = "{\"id\":1,\"name\":\"Naruto Uzumaki\",\"email\":\"naruto@email.com\",\"imageUrl\":\"https://preview.redd.it/which-episode-did-naruto-put-his-hokage-hat-on-v0-4o5fenjwmbyb1.jpg?width=640&crop=smart&auto=webp&s=7d10ede18e34f5794729903d7887f26d7b04fac9\",\"age\":33,\"missions\":{\"id\":23,\"name\":\"Fourth Great Ninja War\",\"rank\":\"S\"},\"rank\":\"KAGE\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ninja not found",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Ninja Not Found",
                                    description = "Example of a ninja not found response",
                                    value = "Ninja with ID 1 not found."
                            )
                    )
            )
    })
    public ResponseEntity<?> updateNinjaById(
            @Parameter(description = "ID of the ninja to be updated", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated ninja details", required = true)
            @RequestBody NinjaDTO ninjaDTO) {
        if (ninjaService.listById(id) != null) {
            NinjaDTO ninja = ninjaService.updateById(id, ninjaDTO);
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja with ID " + id + " not found.");
        }
    }

    // To delete ninja (DELETE)
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete ninja by ID", description = "Deletes a ninja by its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ninja deleted successfully",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Successful Deletion",
                                    description = "Example of a successful ninja deletion response",
                                    value = "Ninja with ID 1 deleted successfully."
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Ninja not found",
                    content = @Content(
                            mediaType = "text/plain",
                            examples = @ExampleObject(
                                    name = "Ninja Not Found",
                                    description = "Example of a ninja not found response",
                                    value = "Ninja with ID 1 not found."
                            )
                    )
            )
    })
    public ResponseEntity<String> deleteNinjaById(
            @Parameter(description = "ID of the ninja to be deleted", required = true)
            @PathVariable Long id) {
        if (ninjaService.listById(id) != null) {
            ninjaService.deleteById(id);
            return ResponseEntity.ok("Ninja with ID " + id + " deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja with ID " + id + " not found.");
        }
    }
}