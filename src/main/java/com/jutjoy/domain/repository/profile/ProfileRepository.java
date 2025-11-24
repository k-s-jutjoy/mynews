package com.jutjoy.domain.repository.profile;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.jutjoy.domain.entity.profile.Profile;
import java.util.Optional;
import org.springframework.data.repository.query.Param;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {

    @Query("SELECT p FROM Profile p LEFT JOIN FETCH p.histories WHERE p.id = :id")
    Optional<Profile> findByIdWithHistories(@Param("id") Integer id);
}
