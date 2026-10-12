package com.bloodlink.backend.repository;

import com.bloodlink.backend.entity.DonorAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DonorAvailabilityRepository extends JpaRepository<DonorAvailability, Long> {

    Optional<DonorAvailability> findByDonorId(Long donorId);
}
