package com.bloodlink.backend.service;

import com.bloodlink.backend.entity.DonorAvailability;
import com.bloodlink.backend.repository.DonorAvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorAvailabilityService {

    private final DonorAvailabilityRepository donorAvailabilityRepository;

    public DonorAvailabilityService(DonorAvailabilityRepository donorAvailabilityRepository) {
        this.donorAvailabilityRepository = donorAvailabilityRepository;
    }

    public List<DonorAvailability> getAllAvailabilities() {
        return donorAvailabilityRepository.findAll();
    }

    public Optional<DonorAvailability> getAvailabilityById(Long id) {
        return donorAvailabilityRepository.findById(id);
    }

    public Optional<DonorAvailability> getAvailabilityByDonorId(Long donorId) {
        return donorAvailabilityRepository.findByDonorId(donorId);
    }

    public DonorAvailability saveAvailability(DonorAvailability availability) {
        return donorAvailabilityRepository.save(availability);
    }

    public void deleteAvailability(Long id) {
        donorAvailabilityRepository.deleteById(id);
    }
}
