package com.example.demo.db;

import com.example.demo.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_chat")
public class GroupChat extends BaseEntity
{
	@Column(name = "name", nullable = false)
	private String name;
}
