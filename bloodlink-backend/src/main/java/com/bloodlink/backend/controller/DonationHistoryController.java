package com.bloodlink.backend.controller;

import com.bloodlink.backend.entity.DonationHistory;
import com.bloodlink.backend.service.DonationHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donation-history")
@CrossOrigin(origins = "*")
public class DonationHistoryController {

    private final DonationHistoryService donationHistoryService;

    public DonationHistoryController(
            DonationHistoryService donationHistoryService) {

        this.donationHistoryService = donationHistoryService;
    }

    @GetMapping
    public List<DonationHistory> getAllDonationHistory() {
        return donationHistoryService.getAllDonationHistory();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonationHistory> getDonationHistoryById(
            @PathVariable Long id) {

        return donationHistoryService.getDonationHistoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/donor/{donorId}")
    public List<DonationHistory> getHistoryByDonor(
            @PathVariable Long donorId) {

        return donationHistoryService.getHistoryByDonor(donorId);
    }

    @PostMapping
    public DonationHistory createDonationHistory(
            @RequestBody DonationHistory donationHistory) {

        return donationHistoryService
                .saveDonationHistory(donationHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonationHistory> updateDonationHistory(
            @PathVariable Long id,
            @RequestBody DonationHistory donationHistory) {

        return donationHistoryService.getDonationHistoryById(id)
                .map(existingHistory -> {
                    donationHistory.setId(id);

                    return ResponseEntity.ok(
                            donationHistoryService
                                    .saveDonationHistory(donationHistory)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonationHistory(
            @PathVariable Long id) {

        if (donationHistoryService
                .getDonationHistoryById(id)
                .isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        donationHistoryService.deleteDonationHistory(id);

        return ResponseEntity.noContent().build();
    }
}