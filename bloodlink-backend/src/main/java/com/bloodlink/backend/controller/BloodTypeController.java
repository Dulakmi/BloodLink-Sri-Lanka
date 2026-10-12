package com.bloodlink.backend.controller;

import com.bloodlink.backend.entity.BloodType;
import com.bloodlink.backend.service.BloodTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-types")
@CrossOrigin(origins = "*")
public class BloodTypeController {

    private final BloodTypeService bloodTypeService;

    public BloodTypeController(BloodTypeService bloodTypeService) {
        this.bloodTypeService = bloodTypeService;
    }

    @GetMapping
    public List<BloodType> getAllBloodTypes() {
        return bloodTypeService.getAllBloodTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodType> getBloodTypeById(
            @PathVariable Integer id) {

        return bloodTypeService.getBloodTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BloodType createBloodType(
            @RequestBody BloodType bloodType) {

        return bloodTypeService.saveBloodType(bloodType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodType> updateBloodType(
            @PathVariable Integer id,
            @RequestBody BloodType bloodType) {

        return bloodTypeService.getBloodTypeById(id)
                .map(existingBloodType -> {
                    existingBloodType.setType(bloodType.getType());

                    return ResponseEntity.ok(
                            bloodTypeService.saveBloodType(existingBloodType)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodType(
            @PathVariable Integer id) {

        if (bloodTypeService.getBloodTypeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        bloodTypeService.deleteBloodType(id);

        return ResponseEntity.noContent().build();
    }
}
