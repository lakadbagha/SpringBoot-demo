/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.String;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Deepak Kumar Maurya
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	Optional<User> findByUserName(String username);
	
    List<User> findByIsDeleted(boolean deleted);
	
}
