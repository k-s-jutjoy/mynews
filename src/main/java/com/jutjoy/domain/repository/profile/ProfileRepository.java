package com.jutjoy.domain.repository.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jutjoy.domain.entity.profile.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
