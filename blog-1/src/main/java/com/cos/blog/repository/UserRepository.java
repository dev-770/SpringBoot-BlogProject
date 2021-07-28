package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO 자동으로 Bean 등록
public interface UserRepository extends JpaRepository<User, Long> {
	// JPA Naming 쿼리 (select * from user where username = ?1 and password = ?2;)
	User findByUsernameAndPassword(String username, String password);
	
	// (select * from user where username = ?1;
	Optional<User> findByUsername(String username);
	
//	@Query(value="select * from username = ?1 and password = ?2;", nativeQuery = true)
//	User login(String username, String password);
}
