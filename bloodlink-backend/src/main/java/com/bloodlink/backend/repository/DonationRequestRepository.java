package com.bloodlink.backend.repository;

import com.bloodlink.backend.entity.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRequestRepository extends JpaRepository<DonationRequest, Long> {

    List<DonationRequest> findByDonorId(Long donorId);

    List<DonationRequest> findByBloodRequestId(Long bloodRequestId);
}
