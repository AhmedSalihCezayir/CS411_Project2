package com.example.demo.serviceimpl.base;

import com.example.demo.db.base.BaseEntity;
import com.example.demo.dto.base.BaseDto;
import com.example.demo.repository.base.BaseRepository;
import com.example.demo.service.base.BaseCrudService;
import com.example.demo.serviceimpl.mapper.base.BaseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class BaseServiceImpl<E extends BaseEntity, D extends BaseDto<UUID>> implements BaseCrudService<D>
{
	protected final BaseRepository<E, UUID> baseRepository;
	private final BaseMapper<E, D> baseMapper;

	@Override
	public D create(D dto) throws EntityNotFoundException
	{
		E entity = baseMapper.dtoToEntity(dto);
		if (entity == null) {
			log.warn("The entity to save cannot be empty!");
			throw new EntityNotFoundException();
		}
		return baseMapper.entityToDto(baseRepository.save(entity));
	}

	@Override
	public List<D> createAll(List<D> dtoList) throws EntityNotFoundException
	{
		List<E> list = new ArrayList<>();
		for (D dto: dtoList)
		{
			E entity = baseMapper.dtoToEntity(dto);
			if (entity == null) {
				log.warn("The entity to save cannot be empty!");
				throw new EntityNotFoundException();
			}
			list.add(entity);
		}
		return baseMapper.entityListToDtoList(baseRepository.saveAll(list));
	}

	@Override
	public D read(UUID id) throws EntityNotFoundException
	{
		if (id == null)
		{
			log.warn("The id cannot be empty!");
			throw new EntityNotFoundException();
		}
		E base = baseRepository.getById(id);
		return baseMapper.entityToDto(base);
	}

	@Override
	public List<D> readAll()
	{
		return baseMapper.entityListToDtoList(baseRepository.findAll());
	}

	@Override
	public D update(D dto) throws EntityNotFoundException
	{
		if (dto == null) {
			log.warn("The entity to update cannot be empty!");
			throw new EntityNotFoundException();
		}
		Optional<E> dbEntity = baseRepository.findById(dto.getId());
		if (!dbEntity.isPresent()) {
			log.warn("The entity to update could not be found!");
			throw new EntityNotFoundException();
		}
		E entity = baseMapper.dtoToEntity(dto);
		baseRepository.save(entity);
		return dto;
	}

	@Override
	public List<D> updateAll(List<D> dtoList)
	{
		ArrayList<D> updatedList = new ArrayList();
		E entity;
		for (D dto: dtoList)
		{
			if (dto == null) {
				log.warn("The entity to update cannot be empty!");
				throw new EntityNotFoundException();
			}
			E dbEntity = baseRepository.getById(dto.getId());
			if (dbEntity == null) {
				log.warn("The entity to update could not be found!");
				throw new EntityNotFoundException();
			}
			entity = baseRepository.getById(dto.getId());
			entity = baseMapper.dtoToEntity(dto);
			baseRepository.save(entity);
			updatedList.add(dto);
		}
		return updatedList;
	}

	@Override
	public D delete(UUID id) throws EntityNotFoundException
	{
		Optional<E> entity = baseRepository.findById(id);
		if (!entity.isPresent())
		{
			log.warn("Entity with id " + id + " was not found!");
			throw new EntityNotFoundException();
		}
		baseRepository.delete(entity.get());
		return baseMapper.entityToDto(entity.get());
	}

	@Override
	public List<D> deleteAll(List<String> StringidList) throws EntityNotFoundException
	{
		List<E> entityList = new ArrayList<>();
		Optional<E> entity;
		D dto;
		for (String id: StringidList)
		{
			entity = baseRepository.findById(UUID.fromString(id));
			if (!entity.isPresent())
			{
				log.warn("Entity with id " + id + " was not found!");
				throw new EntityNotFoundException();
			}
			entityList.add(entity.get());
		}
		for (E deletedEntity : entityList)
		{
			baseRepository.delete(deletedEntity);
		}
		return baseMapper.entityListToDtoList(entityList);
	}

	@Override
	public boolean existsById(UUID id)
	{
		if (id == null)
		{
			log.warn("id cannot be empty!");
			return false;
		}
		return baseRepository.findById(id).isPresent();
	}
}
