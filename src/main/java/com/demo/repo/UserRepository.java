package com.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository already provides basic CRUD operations
}

