package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
	private int id;
	private String title;
	private String content;
	
	@Override
	public String toString() {
		return "BoardDto [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
}
