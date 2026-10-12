package com.bloodlink.backend.service;

import com.bloodlink.backend.entity.HospitalProfile;
import com.bloodlink.backend.repository.HospitalProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalProfileService {

    private final HospitalProfileRepository hospitalProfileRepository;

    public HospitalProfileService(HospitalProfileRepository hospitalProfileRepository) {
        this.hospitalProfileRepository = hospitalProfileRepository;
    }

    public List<HospitalProfile> getAllHospitals() {
        return hospitalProfileRepository.findAll();
    }

    public Optional<HospitalProfile> getHospitalById(Long id) {
        return hospitalProfileRepository.findById(id);
    }

    public Optional<HospitalProfile> getHospitalByUserId(Long userId) {
        return hospitalProfileRepository.findByUserId(userId);
    }

    public HospitalProfile saveHospital(HospitalProfile hospitalProfile) {
        return hospitalProfileRepository.save(hospitalProfile);
    }

    public void deleteHospital(Long id) {
        hospitalProfileRepository.deleteById(id);
    }
}