package com.jutjoy.domain.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.repository.profile.ProfileRepository;

@Service
public class ProfileCreateService {

    @Autowired
    private ProfileRepository profileRepository;

    /*
     * create()
     * 新規プロフィール作成
     * ・Form → Entity 変換
     * ・DB保存
     * ・採番された id を返す
     *
     * Controller側が create 完了後の遷移先を制御できるようにするため
     * （現場では非常に一般的な作り）
     */
    public Integer create(ProfileCreateForm form) {
        Profile profile = new Profile();
        profile.setName(form.getName());
        profile.setGender(form.getGender());
        profile.setHobby(form.getHobby());
        profile.setIntroduction(form.getIntroduction());

        // save()後に id が採番されてセットされる
        Profile saved = profileRepository.save(profile);

        return saved.getId(); // Controller が detail に飛ぶために必要
    }
}
