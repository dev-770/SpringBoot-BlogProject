package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.auth.PrincipalDetailService;

@Configuration // 빈등록 (IoC)
@EnableWebSecurity // 시큐리티 필터 추가
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 -> 권한, 인증 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티 해쉬 값 비교
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트때만) 
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/js/**", "/css/**") // auth 요청 개방
				.permitAll()
				.anyRequest() // 다른 요청 인증
				.authenticated()
			.and()
				.formLogin() // 로그인 폼 이동
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc") // 해당 요청으로 스프링 시큐리티 가로챈다.
				.defaultSuccessUrl("/"); // 로그인 성공시 메인
	}
}
