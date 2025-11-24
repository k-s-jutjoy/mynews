package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.repository.profile.ProfileRepository;

@Service
public class ProfileFindService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile findById(Integer id) {
        return profileRepository.findByIdWithHistories(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID"));
    }
}
