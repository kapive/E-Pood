package com.e.pood.service;

import com.e.pood.dao.domain.model.User;
import com.e.pood.dao.repository.UserRepository;
import com.e.pood.web.api.NewUserRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public void createUser(NewUserRequest userRequest) {
		userRepository.save(User.builder()
				.username(userRequest.getUsername())
				.password(bCryptPasswordEncoder.encode(userRequest.getPassword()))
				.build()
		);
	}


}
