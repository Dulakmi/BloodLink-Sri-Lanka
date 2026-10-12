package com.bloodlink.backend.repository;

import com.bloodlink.backend.entity.DonorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DonorProfileRepository extends JpaRepository<DonorProfile, Long> {

    Optional<DonorProfile> findByUserId(Long userId);

    List<DonorProfile> findByBloodTypeIdAndDistrictIdAndAvailableTrue(
            Integer bloodTypeId,
            Integer districtId
    );
}