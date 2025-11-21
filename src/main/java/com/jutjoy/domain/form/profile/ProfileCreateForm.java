package com.jutjoy.domain.form.profile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


import lombok.Data;

@Data
public class ProfileCreateForm {

    @jakarta.validation.constraints.NotEmpty
    @jakarta.validation.constraints.Size
    private String name;

    @jakarta.validation.constraints.NotEmpty
    private String gender;

    @jakarta.validation.constraints.Size
    private String hobby;

    @jakarta.validation.constraints.Size
    private String introduction;

	public void create(ProfileCreateForm form) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public Object getName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getGender() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getHobby() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getIntroduction() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
