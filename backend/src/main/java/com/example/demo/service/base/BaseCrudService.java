package com.example.demo.service.base;

import com.example.demo.dto.base.BaseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface BaseCrudService<D extends BaseDto<UUID>>
{
	D create(D dto);

	List<D> createAll(List<D> dto);

	D read(UUID id);

	List<D> readAll();

	D update(D dto);

	List<D> updateAll(List<D> dtoList);

	D delete(UUID id);

	List<D> deleteAll(List<String> idList);

	boolean existsById(UUID id);
}
