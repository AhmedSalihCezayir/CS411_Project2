package com.example.demo.controller;

import com.example.demo.controller.base.BaseController;
import com.example.demo.dto.GroupChatDto;
import com.example.demo.dto.base.RestResponse;
import com.example.demo.service.GroupChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("groupChat")
public class GroupChatController extends BaseController<GroupChatDto>
{
	private final GroupChatService groupChatService;

	public GroupChatController(GroupChatService groupChatService) {
		super(groupChatService);
		this.groupChatService = groupChatService;
	}

	@PostMapping("create/{name}")
	public ResponseEntity<RestResponse<GroupChatDto>> create(@RequestBody GroupChatDto dto, @PathVariable String name)
	{
		try
		{
			return new ResponseEntity<>(
					new RestResponse<>(groupChatService.create(dto, name), "Create", "Creating Group Chat was successful."),
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