package com.example.demo.serviceimpl.mapper;

import com.example.demo.db.Friend;
import com.example.demo.dto.FriendDto;
import com.example.demo.serviceimpl.mapper.base.BaseMapper;
import com.example.demo.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface FriendMapper extends BaseMapper<Friend, FriendDto>
{
	FriendMapper INSTANCE = Mappers.getMapper(FriendMapper.class);
}