package com.bloodlink.backend.controller;

import com.bloodlink.backend.entity.HospitalProfile;
import com.bloodlink.backend.service.HospitalProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@CrossOrigin(origins = "*")
public class HospitalProfileController {

    private final HospitalProfileService hospitalProfileService;

    public HospitalProfileController(
            HospitalProfileService hospitalProfileService) {

        this.hospitalProfileService = hospitalProfileService;
    }

    @GetMapping
    public List<HospitalProfile> getAllHospitals() {
        return hospitalProfileService.getAllHospitals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalProfile> getHospitalById(
            @PathVariable Long id) {

        return hospitalProfileService.getHospitalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<HospitalProfile> getHospitalByUserId(
            @PathVariable Long userId) {

        return hospitalProfileService.getHospitalByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public HospitalProfile createHospital(
            @RequestBody HospitalProfile hospitalProfile) {

        return hospitalProfileService.saveHospital(hospitalProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalProfile> updateHospital(
            @PathVariable Long id,
            @RequestBody HospitalProfile hospitalProfile) {

        return hospitalProfileService.getHospitalById(id)
                .map(existingHospital -> {
                    hospitalProfile.setId(id);

                    return ResponseEntity.ok(
                            hospitalProfileService
                                    .saveHospital(hospitalProfile)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(
            @PathVariable Long id) {

        if (hospitalProfileService.getHospitalById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        hospitalProfileService.deleteHospital(id);

        return ResponseEntity.noContent().build();
    }
}
