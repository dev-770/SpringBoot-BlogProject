package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.KakaoService;

@Controller
public class UserController {
	
	@Autowired
	private KakaoService kakaoService;

	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		kakaoService.kakaoCallBackService(code);
	return "redirect:/";
}
	
	@GetMapping("/user/userForm")
	public String userForm() {
		return "user/userForm";
	}
}
