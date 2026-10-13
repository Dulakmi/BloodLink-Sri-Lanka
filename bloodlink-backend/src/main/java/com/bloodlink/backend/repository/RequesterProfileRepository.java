package com.bloodlink.backend.repository;

import com.bloodlink.backend.entity.RequesterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequesterProfileRepository extends JpaRepository<RequesterProfile, Long> {

    Optional<RequesterProfile> findByUserId(Long userId);
}
