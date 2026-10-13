package com.bloodlink.backend.controller;

import com.bloodlink.backend.entity.RequesterProfile;
import com.bloodlink.backend.service.RequesterProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requesters")
@CrossOrigin(origins = "*")
public class RequesterProfileController {

    private final RequesterProfileService requesterProfileService;

    public RequesterProfileController(
            RequesterProfileService requesterProfileService) {

        this.requesterProfileService = requesterProfileService;
    }

    @GetMapping
    public List<RequesterProfile> getAllRequesters() {
        return requesterProfileService.getAllRequesters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequesterProfile> getRequesterById(
            @PathVariable Long id) {

        return requesterProfileService.getRequesterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<RequesterProfile> getRequesterByUserId(
            @PathVariable Long userId) {

        return requesterProfileService.getRequesterByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RequesterProfile createRequester(
            @RequestBody RequesterProfile requesterProfile) {

        return requesterProfileService.saveRequester(requesterProfile);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequesterProfile> updateRequester(
            @PathVariable Long id,
            @RequestBody RequesterProfile requesterProfile) {

        return requesterProfileService.getRequesterById(id)
                .map(existingRequester -> {
                    requesterProfile.setId(id);

                    return ResponseEntity.ok(
                            requesterProfileService
                                    .saveRequester(requesterProfile)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequester(
            @PathVariable Long id) {

        if (requesterProfileService.getRequesterById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        requesterProfileService.deleteRequester(id);

        return ResponseEntity.noContent().build();
    }
}
