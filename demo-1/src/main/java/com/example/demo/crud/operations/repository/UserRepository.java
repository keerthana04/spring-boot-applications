package com.example.demo.crud.operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.crud.operations.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
