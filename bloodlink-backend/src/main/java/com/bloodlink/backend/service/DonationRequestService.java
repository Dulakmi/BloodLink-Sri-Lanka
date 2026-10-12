package com.bloodlink.backend.service;

import com.bloodlink.backend.entity.DonationRequest;
import com.bloodlink.backend.repository.DonationRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationRequestService {

    private final DonationRequestRepository donationRequestRepository;

    public DonationRequestService(DonationRequestRepository donationRequestRepository) {
        this.donationRequestRepository = donationRequestRepository;
    }

    public List<DonationRequest> getAllDonationRequests() {
        return donationRequestRepository.findAll();
    }

    public Optional<DonationRequest> getDonationRequestById(Long id) {
        return donationRequestRepository.findById(id);
    }

    public List<DonationRequest> getRequestsByDonor(Long donorId) {
        return donationRequestRepository.findByDonorId(donorId);
    }

    public List<DonationRequest> getRequestsByBloodRequest(Long bloodRequestId) {
        return donationRequestRepository.findByBloodRequestId(bloodRequestId);
    }

    public DonationRequest saveDonationRequest(DonationRequest donationRequest) {
        return donationRequestRepository.save(donationRequest);
    }

    public void deleteDonationRequest(Long id) {
        donationRequestRepository.deleteById(id);
    }
}
