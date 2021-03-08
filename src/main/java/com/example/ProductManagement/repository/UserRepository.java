package com.example.ProductManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProductManagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
