package com.jutjoy.domain.service.profile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.repository.profile.ProfileRepository;

@Service
public class ProfileListService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<Profile> list() {
        return StreamSupport.stream(profileRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
