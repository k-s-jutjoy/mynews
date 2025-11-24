package com.jutjoy.domain.repository.profile;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.jutjoy.domain.entity.profile.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
}
