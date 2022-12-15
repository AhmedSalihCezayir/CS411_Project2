package com.example.demo.db;

import com.example.demo.db.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
		name = "friend",
		uniqueConstraints = {
				@UniqueConstraint(
						columnNames = {"friend1", "friend2"}
				)
		}
)
public class Friend extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "friend1", referencedColumnName = "name")
	private User friend1;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "friend2", referencedColumnName = "name")
	private User friend2;
}
