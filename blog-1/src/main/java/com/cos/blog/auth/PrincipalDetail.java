package com.cos.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// UserDetails 오브젝트 시큐리티 세션에 저장
@Getter
public class PrincipalDetail implements UserDetails {

	private User user;
	
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	// 계졍 만료 리턴 (true: 만료 X)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정 잠김 (true: 잠김 X)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호 만료 (true: 만료X)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화 (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계졍 권한 목록 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()-> {
				return "ROLE_"+user.getRole(); // ROLE_USER
		});
		return collectors;
	}
}
