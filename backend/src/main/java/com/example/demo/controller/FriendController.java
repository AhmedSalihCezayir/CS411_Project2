package com.example.demo.controller;

import com.example.demo.controller.base.BaseController;
import com.example.demo.dto.FriendDto;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.base.RestResponse;
import com.example.demo.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("friend")
public class FriendController extends BaseController<FriendDto>
{
	private final FriendService friendService;

	public FriendController(FriendService friendService) {
		super(friendService);
		this.friendService = friendService;
	}

	@PostMapping("add/{name1}/{name2}")
	public ResponseEntity<RestResponse<FriendDto>> create(@PathVariable String name1, @PathVariable String name2)
	{
		try
		{
			return new ResponseEntity<>(
					new RestResponse<>(friendService.add(name1, name2), "Create", "Creating Friend Relation was successful."),
					HttpStatus.OK
			);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create",
					"Creating Friend Relation was not successful."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create", "There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("findAll/{name}")
	public ResponseEntity<RestResponse<List<UserDto>>> create(@PathVariable String name)
	{
		try
		{
			return new ResponseEntity<>(
					new RestResponse<>(friendService.findAllFriends(name), "Create", "Creating Friend Relation was successful."),
					HttpStatus.OK
			);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create",
					"Creating Friend Relation was successful."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create", "There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
