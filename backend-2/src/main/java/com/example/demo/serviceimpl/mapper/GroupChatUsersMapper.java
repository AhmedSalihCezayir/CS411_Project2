package com.example.demo.serviceimpl.mapper;

import com.example.demo.db.GroupChatUsers;
import com.example.demo.dto.GroupChatUsersDto;
import com.example.demo.serviceimpl.mapper.base.BaseMapper;
import com.example.demo.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface GroupChatUsersMapper extends BaseMapper<GroupChatUsers, GroupChatUsersDto>
{
	GroupChatUsersMapper INSTANCE = Mappers.getMapper(GroupChatUsersMapper.class);
}
