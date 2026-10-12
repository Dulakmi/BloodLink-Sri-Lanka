package com.bloodlink.backend.service;

import com.bloodlink.backend.entity.BloodRequest;
import com.bloodlink.backend.repository.BloodRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodRequestService {

    private final BloodRequestRepository bloodRequestRepository;

    public BloodRequestService(BloodRequestRepository bloodRequestRepository) {
        this.bloodRequestRepository = bloodRequestRepository;
    }

    public List<BloodRequest> getAllBloodRequests() {
        return bloodRequestRepository.findAll();
    }

    public Optional<BloodRequest> getBloodRequestById(Long id) {
        return bloodRequestRepository.findById(id);
    }

    public List<BloodRequest> getRequestsByRequester(Long requesterId) {
        return bloodRequestRepository.findByRequesterId(requesterId);
    }

    public List<BloodRequest> getRequestsByStatus(
            BloodRequest.RequestStatus status) {

        return bloodRequestRepository.findByStatus(status);
    }

    public BloodRequest saveBloodRequest(BloodRequest bloodRequest) {
        return bloodRequestRepository.save(bloodRequest);
    }

    public void deleteBloodRequest(Long id) {
        bloodRequestRepository.deleteById(id);
    }
}
