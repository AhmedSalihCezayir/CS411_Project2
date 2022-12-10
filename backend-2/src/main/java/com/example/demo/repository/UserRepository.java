package com.example.demo.repository;

import com.example.demo.db.User;
import com.example.demo.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface UserRepository extends BaseRepository<User, UUID>
{
}