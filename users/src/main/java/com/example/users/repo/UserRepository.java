package com.example.users.repo;

import com.example.users.model.User;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Integer> {

    List<User> findAll();
}
