package com.bloodlink.backend.controller;

import com.bloodlink.backend.entity.DonationRequest;
import com.bloodlink.backend.service.DonationRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donation-requests")
@CrossOrigin(origins = "*")
public class DonationRequestController {

    private final DonationRequestService donationRequestService;

    public DonationRequestController(
            DonationRequestService donationRequestService) {

        this.donationRequestService = donationRequestService;
    }

    @GetMapping
    public List<DonationRequest> getAllDonationRequests() {
        return donationRequestService.getAllDonationRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationRequest> getDonationRequestById(
            @PathVariable Long id) {

        return donationRequestService.getDonationRequestById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/donor/{donorId}")
    public List<DonationRequest> getRequestsByDonor(
            @PathVariable Long donorId) {

        return donationRequestService.getRequestsByDonor(donorId);
    }

    @GetMapping("/blood-request/{bloodRequestId}")
    public List<DonationRequest> getRequestsByBloodRequest(
            @PathVariable Long bloodRequestId) {

        return donationRequestService
                .getRequestsByBloodRequest(bloodRequestId);
    }

    @PostMapping
    public DonationRequest createDonationRequest(
            @RequestBody DonationRequest donationRequest) {

        return donationRequestService
                .saveDonationRequest(donationRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationRequest> updateDonationRequest(
            @PathVariable Long id,
            @RequestBody DonationRequest donationRequest) {

        return donationRequestService.getDonationRequestById(id)
                .map(existingRequest -> {
                    donationRequest.setId(id);

                    return ResponseEntity.ok(
                            donationRequestService
                                    .saveDonationRequest(donationRequest)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationRequest(
            @PathVariable Long id) {

        if (donationRequestService
                .getDonationRequestById(id)
                .isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        donationRequestService.deleteDonationRequest(id);

        return ResponseEntity.noContent().build();
    }
}
