package com.example.demo.service;

import com.example.demo.dto.FriendDto;
import com.example.demo.service.base.BaseCrudService;

public interface FriendService extends BaseCrudService<FriendDto>
{
	FriendDto add(String name1, String name2);
}
