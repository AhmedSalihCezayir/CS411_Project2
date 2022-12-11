package com.example.demo.serviceimpl;

import com.example.demo.db.GroupChat;
import com.example.demo.db.GroupChatUsers;
import com.example.demo.db.User;
import com.example.demo.dto.GroupChatDto;
import com.example.demo.dto.UserDto;
import com.example.demo.repository.GroupChatRepository;
import com.example.demo.repository.GroupChatUsersRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.GroupChatService;
import com.example.demo.serviceimpl.base.BaseServiceImpl;
import com.example.demo.serviceimpl.mapper.GroupChatMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class GroupChatServiceImpl extends BaseServiceImpl<GroupChat, GroupChatDto> implements GroupChatService
{
	private final GroupChatRepository groupChatRepository;
	private final GroupChatUsersRepository groupChatUsersRepository;
	private final GroupChatMapper groupChatMapper;
	private final UserRepository userRepository;

	public GroupChatServiceImpl(GroupChatRepository groupChatRepository, GroupChatMapper groupChatMapper, GroupChatUsersRepository groupChatUsersRepository, UserRepository userRepository) {
		super(groupChatRepository, GroupChatMapper.INSTANCE);
		this.groupChatRepository = groupChatRepository;
		this.groupChatMapper = groupChatMapper;
		this.groupChatUsersRepository = groupChatUsersRepository;
		this.userRepository = userRepository;
	}

	@Override
	public GroupChatDto createFromList(List<UserDto> list, String name)
	{
		GroupChat groupChat = new GroupChat();
		groupChat.setName(name);
		groupChatRepository.save(groupChat);
		for (UserDto userDto : list) {
			User user = userRepository.getByName(userDto.getName());
			GroupChatUsers groupChatUsers = new GroupChatUsers();
			groupChatUsers.setGroupChat(groupChat);
			groupChatUsers.setUser(user);
			groupChatUsersRepository.save(groupChatUsers);
		}
		return groupChatMapper.INSTANCE.entityToDto(groupChat);
	}

	@Override
	public GroupChatDto create(GroupChatDto dto, String name) throws EntityNotFoundException
	{
		GroupChat entity = groupChatMapper.INSTANCE.dtoToEntity(dto);
		if (entity == null) {
			throw new EntityNotFoundException("Entity not found");
		}
		GroupChatUsers groupChatUsers = new GroupChatUsers();
		User user = userRepository.getByName(name);
		groupChatUsers.setGroupChat(entity);
		groupChatUsers.setUser(user);
		groupChatRepository.save(entity);
		groupChatUsersRepository.save(groupChatUsers);
		return groupChatMapper.INSTANCE.entityToDto(entity);
	}
}
