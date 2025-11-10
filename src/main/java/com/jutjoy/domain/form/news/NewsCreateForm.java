package com.jutjoy.domain.form.news;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NewsCreateForm {

    @jakarta.validation.constraints.NotEmpty(message = "＊タイトルは必ず入力してください。")
    @jakarta.validation.constraints.Size(max = 20, message = "＊タイトルは20文字以内で設定してください。")
    private String title;

    @jakarta.validation.constraints.NotEmpty(message = "＊本文は必ず入力してください。")
    private String content;

    @SuppressWarnings("unused")
	private MultipartFile image;

	public MultipartFile getImage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getTitle() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public Object getContent() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
