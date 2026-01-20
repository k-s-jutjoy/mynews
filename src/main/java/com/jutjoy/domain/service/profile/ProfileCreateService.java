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
        profile.setName(form.getName()); // 名前
        profile.setGender(form.getGender()); // 性別
        profile.setHobby(form.getHobby()); // 趣味
        profile.setIntroduction(form.getIntroduction()); // 自己紹介
        profileRepository.save(profile); // id は自動採番
    }
}
