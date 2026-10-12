package com.bloodlink.backend.controller;

import com.bloodlink.backend.entity.BloodRequest;
import com.bloodlink.backend.service.BloodRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blood-requests")
@CrossOrigin(origins = "*")
public class BloodRequestController {

    private final BloodRequestService bloodRequestService;

    public BloodRequestController(BloodRequestService bloodRequestService) {
        this.bloodRequestService = bloodRequestService;
    }

    @GetMapping
    public List<BloodRequest> getAllBloodRequests() {
        return bloodRequestService.getAllBloodRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodRequest> getBloodRequestById(
            @PathVariable Long id) {

        return bloodRequestService.getBloodRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/requester/{requesterId}")
    public List<BloodRequest> getRequestsByRequester(
            @PathVariable Long requesterId) {

        return bloodRequestService.getRequestsByRequester(requesterId);
    }

    @GetMapping("/status/{status}")
    public List<BloodRequest> getRequestsByStatus(
            @PathVariable BloodRequest.RequestStatus status) {

        return bloodRequestService.getRequestsByStatus(status);
    }

    @PostMapping
    public BloodRequest createBloodRequest(
            @RequestBody BloodRequest bloodRequest) {

        return bloodRequestService.saveBloodRequest(bloodRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodRequest> updateBloodRequest(
            @PathVariable Long id,
            @RequestBody BloodRequest bloodRequest) {

        return bloodRequestService.getBloodRequestById(id)
                .map(existingRequest -> {
                    bloodRequest.setId(id);

                    return ResponseEntity.ok(
                            bloodRequestService
                                    .saveBloodRequest(bloodRequest)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodRequest(
            @PathVariable Long id) {

        if (bloodRequestService.getBloodRequestById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        bloodRequestService.deleteBloodRequest(id);

        return ResponseEntity.noContent().build();
    }
}
