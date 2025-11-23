package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.repository.profile.ProfileRepository;

@Service
public class ProfileEditService {

    @Autowired
    private ProfileRepository profileRepository;

    public void update(ProfileEditForm form) {

        Profile profile = profileRepository.findById(form.getId()).orElse(null);

        profile.setName(form.getName());
        profile.setGender(form.getGender());
        profile.setHobby(form.getHobby());
        profile.setIntroduction(form.getIntroduction());

        profileRepository.save(profile);
    }
}
