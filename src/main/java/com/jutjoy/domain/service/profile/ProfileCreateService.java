package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.repository.profile.ProfileRepository;
import com.jutjoy.domain.form.profile.ProfileCreateForm;

@Service
public class ProfileCreateService {

    @Autowired
    private ProfileRepository profileRepository;

    public void create(ProfileCreateForm form) {
        Profile profile = new Profile();
        profile.setName(form.getName());
        profile.setGender(form.getGender());
        profile.setHobby(form.getHobby());
        profile.setIntroduction(form.getIntroduction());
        profileRepository.save(profile); // id は自動採番
    }
}
