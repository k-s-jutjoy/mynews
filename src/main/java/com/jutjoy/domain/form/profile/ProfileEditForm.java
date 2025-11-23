package com.jutjoy.domain.form.profile;

import lombok.Data;

@Data
public class ProfileEditForm {

    private Integer id;

    @jakarta.validation.constraints.NotEmpty
    @jakarta.validation.constraints.Size(max = 30)
    private String name;

    @jakarta.validation.constraints.NotEmpty
    private String gender;

    @jakarta.validation.constraints.Size(max = 50)
    private String hobby;

    @jakarta.validation.constraints.Size(max = 200)
    private String introduction;

	public String getName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getGender() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getHobby() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getIntroduction() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Integer getId() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void setId(Object id2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setName(Object name2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setGender(Object gender2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setHobby(Object hobby2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setIntroduction(Object introduction2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
