package com.example.demo.dto;

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
public class FriendDto extends BaseDto<UUID>
{
	private UserDto friend1;
	private UserDto friend2;
}
