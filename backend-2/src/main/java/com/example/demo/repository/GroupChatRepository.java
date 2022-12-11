package com.example.demo.repository;

import com.example.demo.db.GroupChat;
import com.example.demo.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Transactional
@Repository
public interface GroupChatRepository extends BaseRepository<GroupChat, UUID>
{
	GroupChat getGroupChatByName(String name);
}
