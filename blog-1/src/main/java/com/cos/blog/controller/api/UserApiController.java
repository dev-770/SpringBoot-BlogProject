package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
		userService.join(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // javaObject를 JSON 변환 (Jackson)
	}
	
	@PutMapping("/user/update/{id}")
	public ResponseDto<Integer> save(@PathVariable int id, @RequestBody User user) {
		userService.userUpdate(id, user);
		
		/* 세션 만들고 강제 세션 저장  */
		Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); 
	}
	/*
	 * @PostMapping("/auth/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user, HttpSession session) { User principal =
	 * userService.login(user); if (principal != null) {
	 * session.setAttribute("principal", principal); } return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(), 1); }
	 */
}
