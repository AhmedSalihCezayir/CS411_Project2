package com.example.demo.db.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable
{
	@Id
	@GenericGenerator(name = "im-identity", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "im-identity")
	private UUID id;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}
