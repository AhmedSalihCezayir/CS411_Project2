package com.example.demo.dto;

import com.example.demo.db.GroupChat;
import com.example.demo.db.User;
import com.example.demo.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatUsersDto extends BaseDto<UUID>
{
	private GroupChatDto groupChat;
	private UserDto user;
}
