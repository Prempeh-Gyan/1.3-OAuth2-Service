package com.premps.oauth2service.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.premps.oauth2service.model.User;

public interface UserService extends UserDetailsService {

	void saveUser(User user);

	User findOne(long id);

	User findByEmail(String email);

	List<User> findAll();

	void delete(User user);
}