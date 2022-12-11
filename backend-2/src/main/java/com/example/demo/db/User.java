package com.example.demo.db;

import com.example.demo.db.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity
{
	@Column(name = "name", nullable = false, unique = true)
	private String name;
}
