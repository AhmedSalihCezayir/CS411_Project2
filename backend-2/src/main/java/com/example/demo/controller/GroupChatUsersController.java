package com.example.demo.controller;

import com.example.demo.controller.base.BaseController;
import com.example.demo.dto.GroupChatDto;
import com.example.demo.dto.GroupChatUsersDto;
import com.example.demo.dto.base.RestResponse;
import com.example.demo.service.GroupChatUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("groupChatUsers")
public class GroupChatUsersController extends BaseController<GroupChatUsersDto>
{
	private final GroupChatUsersService groupChatUsersService;

	public GroupChatUsersController(GroupChatUsersService groupChatUsersService) {
		super(groupChatUsersService);
		this.groupChatUsersService = groupChatUsersService;
	}

	@PostMapping("add/{name}")
	public ResponseEntity<RestResponse<GroupChatUsersDto>> create(@RequestBody GroupChatDto dto, @PathVariable String name)
	{
		try
		{
			return new ResponseEntity<>(
					new RestResponse<>(groupChatUsersService.add(dto, name), "Create", "Creating Group Chat was successful."),
					HttpStatus.OK
			);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create",
					"Creating Group Chat was successful."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create", "There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
