package com.example.demo.controller;

import com.example.demo.controller.base.BaseController;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController extends BaseController<UserDto>
{
	private final UserService userService;

	public UserController(UserService userService) {
		super(userService);
		this.userService = userService;
	}
}
