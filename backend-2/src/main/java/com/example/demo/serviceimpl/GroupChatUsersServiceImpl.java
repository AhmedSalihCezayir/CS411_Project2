package com.example.demo.serviceimpl;

import com.example.demo.db.GroupChat;
import com.example.demo.db.GroupChatUsers;
import com.example.demo.db.User;
import com.example.demo.dto.GroupChatDto;
import com.example.demo.dto.GroupChatUsersDto;
import com.example.demo.repository.GroupChatRepository;
import com.example.demo.repository.GroupChatUsersRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GroupChatUsersService;
import com.example.demo.serviceimpl.base.BaseServiceImpl;
import com.example.demo.serviceimpl.mapper.GroupChatUsersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GroupChatUsersServiceImpl extends BaseServiceImpl<GroupChatUsers, GroupChatUsersDto> implements GroupChatUsersService
{
	private final GroupChatUsersRepository groupChatUsersRepository;
	private final UserRepository userRepository;
	private final GroupChatRepository groupChatRepository;
	private final GroupChatUsersMapper groupChatUsersMapper;

	public GroupChatUsersServiceImpl(GroupChatUsersRepository groupChatUsersRepository, GroupChatUsersMapper groupChatUsersMapper, UserRepository userRepository, GroupChatRepository groupChatRepository) {
		super(groupChatUsersRepository, GroupChatUsersMapper.INSTANCE);
		this.groupChatUsersRepository = groupChatUsersRepository;
		this.groupChatUsersMapper = groupChatUsersMapper;
		this.userRepository = userRepository;
		this.groupChatRepository = groupChatRepository;
	}

	@Override
	public GroupChatUsersDto add(GroupChatDto dto, String name)
	{
		GroupChatUsers groupChatUsers = new GroupChatUsers();
		GroupChat groupChat = groupChatRepository.getGroupChatByName(dto.getName());
		groupChatUsers.setGroupChat(groupChat);
		User user = userRepository.getByName(name);
		groupChatUsers.setUser(user);
		groupChatUsersRepository.save(groupChatUsers);
		return groupChatUsersMapper.INSTANCE.entityToDto(groupChatUsers);
	}
}
