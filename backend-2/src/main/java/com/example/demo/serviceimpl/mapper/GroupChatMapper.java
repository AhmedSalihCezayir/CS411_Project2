package com.example.demo.serviceimpl.mapper;

import com.example.demo.db.GroupChat;
import com.example.demo.dto.GroupChatDto;
import com.example.demo.serviceimpl.mapper.base.BaseMapper;
import com.example.demo.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface GroupChatMapper extends BaseMapper<GroupChat, GroupChatDto>
{
	GroupChatMapper INSTANCE = Mappers.getMapper(GroupChatMapper.class);
}

