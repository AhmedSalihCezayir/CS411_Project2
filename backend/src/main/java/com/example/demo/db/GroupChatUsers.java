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
		name = "group_chat_users",
		uniqueConstraints = {
				@UniqueConstraint(
						columnNames = {"group_chat_name", "user_name"}
				)
		}
)
public class GroupChatUsers extends BaseEntity
{
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "group_chat_name", referencedColumnName = "name")
	private GroupChat groupChat;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_name", referencedColumnName = "name")
	private User user;
}
