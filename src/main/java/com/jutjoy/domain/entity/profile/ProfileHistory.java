package com.jutjoy.domain.entity.profile;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "profile_histories")
public class ProfileHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String introduction; // 更新内容など
    private LocalDateTime editedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    // --- getter / setter ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }

    public LocalDateTime getEditedDate() { return editedDate; }
    public void setEditedDate(LocalDateTime editedDate) { this.editedDate = editedDate; }

    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }
}
