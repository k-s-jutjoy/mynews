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

    /*
     * update()
     * プロフィール編集
     * ・Form → Entity 反映
     * ・編集履歴も同時登録
     * ・更新後の id を返す
     *
     * create と統一して Controller が遷移先を制御できる作りにする
     * 現場では PRG + 完了後詳細画面戻しはよくある構成
     */
    public Integer update(ProfileEditForm form) {

        // ① 編集対象を取得（なければ例外）
        Profile profile = profileRepository.findById(form.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID"));

        // ② Form の値を Entity に反映
        profile.setName(form.getName());
        profile.setGender(form.getGender());
        profile.setHobby(form.getHobby());
        profile.setIntroduction(form.getIntroduction());

        // ③ 編集履歴追加（必要に応じて文字列化方針変更可能）
        ProfileHistory history = new ProfileHistory();
        history.setProfile(profile);
        history.setEditedDate(LocalDateTime.now());
        history.setIntroduction("更新"); 
        profile.getHistories().add(history);

        // ④ 更新保存
        Profile saved = profileRepository.save(profile);

        // ⑤ Controller が detail に戻るため id を返す
        return saved.getId();
    }
}
