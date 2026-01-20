package com.jutjoy.domain.repository.profile;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.jutjoy.domain.entity.profile.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    // ID昇順で取得
    List<Profile> findAllByOrderByIdAsc();

    // 履歴をまとめて取得（N+1回避）
    @Query("SELECT p FROM Profile p LEFT JOIN FETCH p.histories WHERE p.id = :id")
    Optional<Profile> findByIdWithHistories(@Param("id") Integer id);

    // ページングは JpaRepository#findAll(Pageable) で対応
}
