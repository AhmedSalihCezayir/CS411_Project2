package com.example.demo.repository;

import com.example.demo.db.Friend;
import com.example.demo.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public interface FriendRepository extends BaseRepository<Friend, UUID>
{
	List<Friend> findAllByFriend1_Name(String friend1Name);
}
