package com.jutjoy.domain.form.profile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import lombok.Data;

@Data
public class ProfileCreateForm {

    @NotEmpty
    @Size
    private String name;

    @NotEmpty
    private String gender;

    @Size
    private String hobby;

    @Size
    private String introduction;

//	public void create(ProfileCreateForm form) {
//		// TODO 自動生成されたメソッド・スタブ
//		
//	}


    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return this.hobby;
    }
    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getIntroduction() {
        return this.introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}