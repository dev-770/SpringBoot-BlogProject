package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // javaObject를 JSON 변환 (Jackson)
	}
	
	/*
	 * @PostMapping("/auth/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession session) { User principal =
	 * userService.login(user); if (principal != null) {
	 * session.setAttribute("principal", principal); } return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */
}
