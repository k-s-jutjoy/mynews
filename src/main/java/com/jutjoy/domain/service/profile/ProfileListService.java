package com.jutjoy.domain.service.profile;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import com.jutjoy.domain.entity.profile.Profile;

@Service
public class ProfileListService {

    @SuppressWarnings("rawtypes")
	@Autowired
    private CrudRepository profileRepository;

    @SuppressWarnings("unchecked")
	public List<Profile> findAll() {
        return (List<Profile>) profileRepository.findAll();
    }
}
