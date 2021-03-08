package com.example.ProductManagement.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.ProductManagement.model.Products;
import com.example.ProductManagement.model.User;
import com.example.ProductManagement.repository.ProductRepository;
import com.example.ProductManagement.repository.UserRepository;

@Component
public class UserImplementation {
	
	@Autowired
	private  UserRepository userRepository;
	
	public List<User> getUsers(){
		return userRepository.findAll();
	}

	public  void saveUser(User user ) {
		userRepository.save(user);
	}
	
	public User getUser(Integer id) {
		return userRepository.findById(id).get();
	}
	
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	
}
