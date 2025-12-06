package com.jutjoy.domain.service.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.repository.profile.ProfileRepository;

@Service
public class ProfileListService {

    @Autowired
    private ProfileRepository profileRepository;

    // ページング対応
    public Page<Profile> list(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    // 全件取得（非ページング用）
    public List<Profile> list() {
        return profileRepository.findAllByOrderByIdAsc();
    }
}
