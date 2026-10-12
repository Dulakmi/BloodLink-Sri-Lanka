package com.bloodlink.backend.service;

import com.bloodlink.backend.entity.DonorProfile;
import com.bloodlink.backend.repository.DonorProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonorProfileService {

    private final DonorProfileRepository donorProfileRepository;

    public DonorProfileService(DonorProfileRepository donorProfileRepository) {
        this.donorProfileRepository = donorProfileRepository;
    }

    public List<DonorProfile> getAllDonors() {
        return donorProfileRepository.findAll();
    }

    public Optional<DonorProfile> getDonorById(Long id) {
        return donorProfileRepository.findById(id);
    }

    public Optional<DonorProfile> getDonorByUserId(Long userId) {
        return donorProfileRepository.findByUserId(userId);
    }

    public List<DonorProfile> searchAvailableDonors(
            Integer bloodTypeId,
            Integer districtId) {

        return donorProfileRepository
                .findByBloodTypeIdAndDistrictIdAndAvailableTrue(
                        bloodTypeId,
                        districtId
                );
    }

    public DonorProfile saveDonor(DonorProfile donorProfile) {
        return donorProfileRepository.save(donorProfile);
    }

    public void deleteDonor(Long id) {
        donorProfileRepository.deleteById(id);
    }
}
