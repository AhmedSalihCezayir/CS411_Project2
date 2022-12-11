package com.example.demo.service;

import com.example.demo.dto.GroupChatDto;
import com.example.demo.service.base.BaseCrudService;

public interface GroupChatService extends BaseCrudService<GroupChatDto>
{
	GroupChatDto create(GroupChatDto dto, String name);
}
