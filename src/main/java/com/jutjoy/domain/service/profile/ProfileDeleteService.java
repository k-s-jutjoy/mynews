package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.repository.profile.ProfileRepository;

@Service
public class ProfileDeleteService {

    @Autowired
    private ProfileRepository profileRepository;

    public void delete(Integer id) {
        profileRepository.deleteById(id);
    }
}
