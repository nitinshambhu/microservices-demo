package com.example.users.repo;

import com.example.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface ReadonlyUserRepository extends Repository<User, Integer> {

    Optional<User> findById(final Integer userId);

    List<User> findAll();
}
