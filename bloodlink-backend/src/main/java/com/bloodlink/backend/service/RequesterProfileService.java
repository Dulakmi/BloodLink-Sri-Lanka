package com.bloodlink.backend.service;

import com.bloodlink.backend.entity.RequesterProfile;
import com.bloodlink.backend.repository.RequesterProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequesterProfileService {

    private final RequesterProfileRepository requesterProfileRepository;

    public RequesterProfileService(RequesterProfileRepository requesterProfileRepository) {
        this.requesterProfileRepository = requesterProfileRepository;
    }

    public List<RequesterProfile> getAllRequesters() {
        return requesterProfileRepository.findAll();
    }

    public Optional<RequesterProfile> getRequesterById(Long id) {
        return requesterProfileRepository.findById(id);
    }

    public Optional<RequesterProfile> getRequesterByUserId(Long userId) {
        return requesterProfileRepository.findByUserId(userId);
    }

    public RequesterProfile saveRequester(RequesterProfile requesterProfile) {
        return requesterProfileRepository.save(requesterProfile);
    }

    public void deleteRequester(Long id) {
        requesterProfileRepository.deleteById(id);
    }
}
