package com.example.demo.serviceimpl;

import com.example.demo.db.User;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.serviceimpl.base.BaseServiceImpl;
import com.example.demo.serviceimpl.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<User, UserDto> implements UserService
{
	private final UserRepository userRepository;

	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		super(userRepository, UserMapper.INSTANCE);
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
}
