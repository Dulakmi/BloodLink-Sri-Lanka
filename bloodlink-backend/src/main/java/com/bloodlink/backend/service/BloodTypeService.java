package com.bloodlink.backend.service;

import com.bloodlink.backend.entity.BloodType;
import com.bloodlink.backend.repository.BloodTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BloodTypeService {

    private final BloodTypeRepository bloodTypeRepository;

    public BloodTypeService(BloodTypeRepository bloodTypeRepository) {
        this.bloodTypeRepository = bloodTypeRepository;
    }

    public List<BloodType> getAllBloodTypes() {
        return bloodTypeRepository.findAll();
    }

    public Optional<BloodType> getBloodTypeById(Integer id) {
        return bloodTypeRepository.findById(id);
    }

    public BloodType saveBloodType(BloodType bloodType) {
        return bloodTypeRepository.save(bloodType);
    }

    public void deleteBloodType(Integer id) {
        bloodTypeRepository.deleteById(id);
    }
}
