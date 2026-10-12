package com.bloodlink.backend.repository;

import com.bloodlink.backend.entity.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodRequestRepository extends JpaRepository<BloodRequest, Long> {

    List<BloodRequest> findByRequesterId(Long requesterId);

    List<BloodRequest> findByStatus(BloodRequest.RequestStatus status);
}
