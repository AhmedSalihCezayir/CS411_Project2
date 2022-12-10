package com.example.demo.controller.base;

import com.example.demo.dto.base.BaseDto;
import com.example.demo.dto.base.RestResponse;
import com.example.demo.service.base.BaseCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@CrossOrigin()
public abstract class BaseController<D extends BaseDto<UUID>>
{
	private final BaseCrudService<D> baseCrudService;

	public BaseController(BaseCrudService<D> baseCrudService)
	{
		this.baseCrudService = baseCrudService;
	}

	/**
	 * The base create method. Saves the entity mapped from the dto dto.
	 * @param dto is the entity received from the frontend.
	 * @return the entity created in dto form.
	 */
	@PostMapping(value = "create")
	public ResponseEntity<RestResponse<D>> create(@RequestBody D dto) {
		try
		{
			return new ResponseEntity<>(
					new RestResponse<>(baseCrudService.create(dto), "Create", "Creating Entity was successful."),
					HttpStatus.OK
			);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create",
					"Creating Entity was unsuccessful due to an error with the entity given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create", "There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Create all method is the same with the create method, but it receives and returns a list.
	 * @param dtoList is the list of dtos received from frontend.
	 * @return the list of the entities created.
	 */
	@PostMapping(value = "manufacture")
	public ResponseEntity<RestResponse<List<D>>> createAll(@RequestBody List<D> dtoList) {
		try
		{
			return new ResponseEntity<>(
					new RestResponse<>(baseCrudService.createAll(dtoList),"Create All", "Create All was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create All",
					"Creating entities was unsuccessful due to an error with the entities given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Create All", "There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Reads the entity with the UUID id.
	 * @param id the UUID of the entity which is going to be returned.
	 * @return the entity in dto form with the UUID id.
	 */
	@GetMapping(value = "read/{id}")
	public ResponseEntity<RestResponse<D>> read(@PathVariable String id)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.read(UUID.fromString(id)), "Read",
					"Reading an entity was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Read",
					"Reading entities was unsuccessful due to an error with the entities given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Read","There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Reads all entities in a database table and returns them in a list.
	 * @return the list of entities in a table in dto form.
	 */
	@GetMapping(value = "read")
	public ResponseEntity<List<D>> readAll()
	{
		try
		{
			return new ResponseEntity<>(baseCrudService.readAll(), HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Updates an entity.
	 * @param dto the entity going to be updated.
	 * @return the updated entity in dto form.
	 */
	@PutMapping(value = "update")
	public ResponseEntity<RestResponse<D>> update(@RequestBody D dto) {
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.update(dto),"Update", "Updating an entity was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,"Update",
					"Updating an entity was unsuccessful due to an error with the entity given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,"Update",
					"There was an unexpected error."), HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Same as the update method, but this method takes a list as a parameter.
	 * @param dtoList the list of entites going to be updated.
	 * @return the updated entities in dto form.
	 */
	@PutMapping(value = "updateAll")
	public ResponseEntity<RestResponse<List<D>>> updateAll(@RequestBody List<D> dtoList) {
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.updateAll(dtoList),"Update All",
					"Updating entities was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Update All",
					"Updating entities was unsuccessful due to an error with the entities given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null, "Update All",
					"There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Deletes the entity with the given UUID id
	 * @param id the id of the entity going to be deleted
	 * @return the deleted entity in dto form.
	 */
	@DeleteMapping(value = "delete/{id}")
	public ResponseEntity<RestResponse<D>> delete(@PathVariable String id)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.delete(UUID.fromString(id)),"Delete",
					"Deleting an entity was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,
					"Deleting an entity was unsuccessful due to an error with the entity given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,
					"There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}

	/**
	 * Same as the delete method, but this method takes a list as a parameter.
	 * @param idList the list of UUIDs of the entities going to be deleted.
	 * @return the deleted entities in dto form.
	 */
	@DeleteMapping(value = "deleteAll")
	public ResponseEntity<RestResponse<List<D>>> deleteAll(@RequestBody List<String> idList)
	{
		try
		{
			return new ResponseEntity<>(new RestResponse<>(baseCrudService.deleteAll(idList),"Delete from list",
					"Deleting an entity list was successful."),
					HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,
					"Deleting an entity list was unsuccessful due to an error with the entity given."),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
		catch (Exception e)
		{
			return new ResponseEntity<>(new RestResponse<>(null,
					"There was an unexpected error."),
					HttpStatus.EXPECTATION_FAILED);
		}
	}
}
