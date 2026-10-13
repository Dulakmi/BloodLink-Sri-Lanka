package com.bloodlink.backend.service;

import com.bloodlink.backend.entity.DonationHistory;
import com.bloodlink.backend.repository.DonationHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationHistoryService {

    private final DonationHistoryRepository donationHistoryRepository;

    public DonationHistoryService(DonationHistoryRepository donationHistoryRepository) {
        this.donationHistoryRepository = donationHistoryRepository;
    }

    public List<DonationHistory> getAllDonationHistory() {
        return donationHistoryRepository.findAll();
    }

    public Optional<DonationHistory> getDonationHistoryById(Long id) {
        return donationHistoryRepository.findById(id);
    }

    public List<DonationHistory> getHistoryByDonor(Long donorId) {
        return donationHistoryRepository.findByDonorId(donorId);
    }

    public DonationHistory saveDonationHistory(DonationHistory donationHistory) {
        return donationHistoryRepository.save(donationHistory);
    }

    public void deleteDonationHistory(Long id) {
        donationHistoryRepository.deleteById(id);
    }
}
