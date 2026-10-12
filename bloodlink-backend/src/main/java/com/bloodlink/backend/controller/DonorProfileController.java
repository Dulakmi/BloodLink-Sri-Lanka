package com.bloodlink.backend.controller;

import com.bloodlink.backend.entity.DonorProfile;
import com.bloodlink.backend.service.DonorProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "*")
public class DonorProfileController {

    private final DonorProfileService donorProfileService;

    public DonorProfileController(DonorProfileService donorProfileService) {
        this.donorProfileService = donorProfileService;
    }

    @GetMapping
    public List<DonorProfile> getAllDonors() {
        return donorProfileService.getAllDonors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonorProfile> getDonorById(
            @PathVariable Long id) {

        return donorProfileService.getDonorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<DonorProfile> getDonorByUserId(
            @PathVariable Long userId) {

        return donorProfileService.getDonorByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<DonorProfile> searchAvailableDonors(
            @RequestParam Integer bloodTypeId,
            @RequestParam Integer districtId) {

        return donorProfileService.searchAvailableDonors(
                bloodTypeId,
                districtId
        );
    }

    @PostMapping
    public DonorProfile createDonor(
            @RequestBody DonorProfile donorProfile) {

        return donorProfileService.saveDonor(donorProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonorProfile> updateDonor(
            @PathVariable Long id,
            @RequestBody DonorProfile donorProfile) {

        return donorProfileService.getDonorById(id)
                .map(existingDonor -> {
                    donorProfile.setId(id);

                    return ResponseEntity.ok(
                            donorProfileService.saveDonor(donorProfile)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(
            @PathVariable Long id) {

        if (donorProfileService.getDonorById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        donorProfileService.deleteDonor(id);

        return ResponseEntity.noContent().build();
    }
}
