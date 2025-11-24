package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.entity.profile.ProfileHistory;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.repository.profile.ProfileRepository;
import java.time.LocalDateTime;

@Service
public class ProfileEditService {

    @Autowired
    private ProfileRepository profileRepository;

    public void update(ProfileEditForm form) {
        Profile profile = profileRepository.findById(form.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID"));

        profile.setName(form.getName());
        profile.setGender(form.getGender());
        profile.setHobby(form.getHobby());
        profile.setIntroduction(form.getIntroduction());

        // 履歴作成
        ProfileHistory history = new ProfileHistory();
        history.setProfile(profile);
        history.setEditedDate(LocalDateTime.now());
        history.setIntroduction("更新"); // 必要に応じて編集内容を文字列で残す

        profile.getHistories().add(history);

        profileRepository.save(profile);
    }
}
