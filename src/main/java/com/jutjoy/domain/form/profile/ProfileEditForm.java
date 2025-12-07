package com.jutjoy.domain.form.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProfileEditForm {

    private Integer id;

	@NotEmpty(message = "名前を入力してください")
	@Size(max = 20, message = "名前は20文字以内で入力してください")
	private String name;

	@NotEmpty(message = "性別を入力してください")
	@Size(max = 10, message = "性別は10文字以内で入力してください")
	private String gender;

	@Size(max = 100, message = "趣味は100文字以内で入力してください")
	private String hobby;

	@NotBlank(message = "自己紹介は必須です")
	@Size(max = 200, message = "自己紹介は200文字以内で入力してください")
	private String introduction;

    // getter / setter
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getHobby() { return hobby; }
    public void setHobby(String hobby) { this.hobby = hobby; }

    public String getIntroduction() { return introduction; }
    public void setIntroduction(String introduction) { this.introduction = introduction; }
}
