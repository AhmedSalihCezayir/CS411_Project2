package com.example.demo.repository;

import com.example.demo.db.GroupChatUsers;
import com.example.demo.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface GroupChatUsersRepository extends BaseRepository<GroupChatUsers, UUID>
{
	List<GroupChatUsers> findAllByUser_Name(String name);
}
