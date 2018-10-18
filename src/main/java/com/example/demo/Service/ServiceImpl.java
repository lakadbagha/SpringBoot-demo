/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Deepak Kumar Maurya
 */
@Service
public class ServiceImpl {
    
    @Autowired
    UserRepository repository;
    
    public User findAccountByEmail(String userName) {
		return repository.findByUserName(userName).orElse(null);
	}

	public List<User> findAllAccount() {
		return repository.findByIsDeleted(false);
	}
	
	public void saveUser(User user) {
		Calendar cal = Calendar.getInstance();
		Timestamp createdDate = new Timestamp(cal.getTime().getTime());
		user.setCreatedDate(createdDate);
		repository.save(user);
	}
	
	public void updateUser(User user) {
		Calendar cal = Calendar.getInstance();
		Timestamp modifiedDate = new Timestamp(cal.getTime().getTime());
		user.setModifiedDate(modifiedDate);
		repository.save(user);
	}
	
	public void deleteUser(User user) {
		Calendar cal = Calendar.getInstance();
		Timestamp modifiedDate = new Timestamp(cal.getTime().getTime());
		user.setModifiedDate(modifiedDate);
		user.setIsDeleted(true);
		repository.save(user);
	}
    
}
