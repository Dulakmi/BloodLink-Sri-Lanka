package com.bloodlink.backend.controller;

import com.bloodlink.backend.entity.District;
import com.bloodlink.backend.service.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
@CrossOrigin(origins = "*")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping
    public List<District> getAllDistricts() {
        return districtService.getAllDistricts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<District> getDistrictById(
            @PathVariable Integer id) {

        return districtService.getDistrictById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public District createDistrict(
            @RequestBody District district) {

        return districtService.saveDistrict(district);
    }

    @PutMapping("/{id}")
    public ResponseEntity<District> updateDistrict(
            @PathVariable Integer id,
            @RequestBody District district) {

        return districtService.getDistrictById(id)
                .map(existingDistrict -> {
                    existingDistrict.setName(district.getName());
                    existingDistrict.setProvince(district.getProvince());

                    return ResponseEntity.ok(
                            districtService.saveDistrict(existingDistrict)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDistrict(
            @PathVariable Integer id) {

        if (districtService.getDistrictById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        districtService.deleteDistrict(id);

        return ResponseEntity.noContent().build();
    }
}
