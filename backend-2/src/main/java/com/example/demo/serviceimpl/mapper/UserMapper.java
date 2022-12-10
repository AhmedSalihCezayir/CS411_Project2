package com.example.demo.serviceimpl.mapper;

import com.example.demo.db.User;
import com.example.demo.dto.UserDto;
import com.example.demo.serviceimpl.mapper.base.BaseMapper;
import com.example.demo.serviceimpl.mapper.base.MapConfig;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapConfig.class, componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto>
{
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
