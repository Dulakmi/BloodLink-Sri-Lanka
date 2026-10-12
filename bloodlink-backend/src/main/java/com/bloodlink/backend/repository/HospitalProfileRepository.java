package com.bloodlink.backend.repository;

import com.bloodlink.backend.entity.HospitalProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalProfileRepository extends JpaRepository<HospitalProfile, Long> {

    Optional<HospitalProfile> findByUserId(Long userId);
}
