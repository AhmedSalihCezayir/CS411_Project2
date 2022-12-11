package com.example.demo.service;

import com.example.demo.dto.GroupChatDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.base.BaseCrudService;

import java.util.List;

public interface GroupChatService extends BaseCrudService<GroupChatDto>
{
	GroupChatDto create(GroupChatDto dto, String name);
	GroupChatDto createFromList(List<UserDto> list, String name);
}
