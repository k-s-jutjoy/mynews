package com.jutjoy.domain.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name="news")
@Data
@jakarta.persistence.EntityListeners(AuditingEntityListener.class)
public class News {

    @jakarta.persistence.Id
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @jakarta.persistence.Column(name = "id")
    private Integer id;

    @jakarta.persistence.Column(name = "title")
    private String title;

    @jakarta.persistence.Column(name = "content")
    private String content;

    @jakarta.persistence.Column(name = "image_name")
    private String imageName;

    @CreatedDate
    @jakarta.persistence.Column(name = "registered_date")
    private Timestamp registeredDate;

    @LastModifiedDate
    @jakarta.persistence.Column(name = "updated_date")
    private Timestamp updatedDate;

	public String getId() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void setTitle(Object title2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setContent(Object content2) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void setImageName(Object object) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public String getImageName() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public String getImageName1() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}