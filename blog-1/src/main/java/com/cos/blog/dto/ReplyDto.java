package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
	private int userId;
	private int boardId;
	private String content;
	
	@Override
	public String toString() {
		return "ReplyDto [userId=" + userId + ", boardId=" + boardId + ", content=" + content + "]";
	}
}
