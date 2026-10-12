package com.bloodlink.backend.repository;

import com.bloodlink.backend.entity.BloodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloodTypeRepository extends JpaRepository<BloodType, Integer> {
}
