package com.jutjoy.domain.repository.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.jutjoy.domain.entity.profile.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Integer> {
    List<Profile> findAllByOrderByIdAsc();

    @Query("SELECT p FROM Profile p LEFT JOIN FETCH p.histories WHERE p.id = :id")
    Optional<Profile> findByIdWithHistories(@Param("id") Integer id);
}
