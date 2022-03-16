package com.e.pood.web.controller;

import com.e.pood.service.UserService;
import com.e.pood.web.api.NewUserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin()
@RequestMapping("/api/v1/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping()
	public void addProductToCart(@RequestBody NewUserRequest userRequest) {
		userService.createUser(userRequest);
	}
}
