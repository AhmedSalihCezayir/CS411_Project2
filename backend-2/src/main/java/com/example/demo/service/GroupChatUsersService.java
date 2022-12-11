package com.example.demo.service;

import com.example.demo.dto.GroupChatDto;
import com.example.demo.dto.GroupChatUsersDto;
import com.example.demo.service.base.BaseCrudService;

public interface GroupChatUsersService extends BaseCrudService<GroupChatUsersDto>
{
	GroupChatUsersDto add(GroupChatDto dto, String name);
}
