package com.example.demo.serviceimpl;

import com.example.demo.db.Friend;
import com.example.demo.db.User;
import com.example.demo.dto.FriendDto;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.FriendRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FriendService;
import com.example.demo.serviceimpl.base.BaseServiceImpl;
import com.example.demo.serviceimpl.mapper.FriendMapper;
import com.example.demo.serviceimpl.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class FriendServiceImpl extends BaseServiceImpl<Friend, FriendDto> implements FriendService
{
	private final FriendRepository friendRepository;
	private final UserRepository userRepository;
	private final FriendMapper friendMapper;
	private final UserMapper userMapper;
	public FriendServiceImpl(FriendRepository friendRepository, FriendMapper friendMapper, UserRepository userRepository, UserMapper userMapper) {
		super(friendRepository, FriendMapper.INSTANCE);
		this.friendRepository = friendRepository;
		this.friendMapper = friendMapper;
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public FriendDto add(String name1, String name2)
	{
		Friend friend = new Friend();
		User user1 = userRepository.getByName(name1);
		User user2 = userRepository.getByName(name2);
		friend.setFriend1(user1);
		friend.setFriend2(user2);
		super.create(friendMapper.INSTANCE.entityToDto(friend));
		friend.setFriend1(user2);
		friend.setFriend2(user1);
		return super.create(friendMapper.INSTANCE.entityToDto(friend));
	}

	@Override
	public List<UserDto> findAllFriends(String name)
	{
		List<Friend> list = friendRepository.findAllByFriend1_Name(name);
		List<User> result = new ArrayList<>();
		for (Friend friend : list) {
			result.add(friend.getFriend2());
		}
		return userMapper.INSTANCE.entityListToDtoList(result);
	}
}
