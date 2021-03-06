package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional(readOnly = true)
	public User searchId(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
			return user;
	}
	
	@Transactional // All or NotAll
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword= encoder.encode(rawPassword); // 해쉬로 변경
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void userUpdate(int id, User user) {
		User persistance = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("Error : 유저를 찾을 수 없습니다.");
		});
		String rawPassword = user.getPassword();
		String endPassword = encoder.encode(rawPassword);
		persistance.setPassword(endPassword);
		persistance.setEmail(user.getEmail());
		
	}
	
	// Select 트랜잭션 시작, 서비스 종료시 트랜잭션 종료
	/*
	 * @Transactional(readOnly = true) public User login (User user) {
	 * System.out.println("username = "+user.getUsername() + " Pass : " +
	 * user.getPassword()); return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),
	 * user.getPassword()); }
	 */
}
