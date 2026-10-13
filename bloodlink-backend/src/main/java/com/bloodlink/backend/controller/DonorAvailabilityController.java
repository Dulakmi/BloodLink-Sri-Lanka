package com.bloodlink.backend.controller;

import com.bloodlink.backend.entity.DonorAvailability;
import com.bloodlink.backend.service.DonorAvailabilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donor-availability")
@CrossOrigin(origins = "*")
public class DonorAvailabilityController {

    private final DonorAvailabilityService donorAvailabilityService;

    public DonorAvailabilityController(
            DonorAvailabilityService donorAvailabilityService) {

        this.donorAvailabilityService = donorAvailabilityService;
    }

    @GetMapping
    public List<DonorAvailability> getAllAvailabilities() {
        return donorAvailabilityService.getAllAvailabilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonorAvailability> getAvailabilityById(
            @PathVariable Long id) {

        return donorAvailabilityService.getAvailabilityById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<DonorAvailability> getAvailabilityByDonorId(
            @PathVariable Long donorId) {

        return donorAvailabilityService
                .getAvailabilityByDonorId(donorId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DonorAvailability createAvailability(
            @RequestBody DonorAvailability availability) {

        return donorAvailabilityService.saveAvailability(availability);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonorAvailability> updateAvailability(
            @PathVariable Long id,
            @RequestBody DonorAvailability availability) {

        return donorAvailabilityService.getAvailabilityById(id)
                .map(existingAvailability -> {
                    availability.setId(id);

                    return ResponseEntity.ok(
                            donorAvailabilityService
                                    .saveAvailability(availability)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvailability(
            @PathVariable Long id) {

        if (donorAvailabilityService
                .getAvailabilityById(id)
                .isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        donorAvailabilityService.deleteAvailability(id);

        return ResponseEntity.noContent().build();
    }
}
