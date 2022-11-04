/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfell.usersapi.dao;

import com.interfell.usersapi.modelo.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author vbocanegra
 */
public interface UserDao extends JpaRepository<User, Long> {
    
    List<User> findByEmail(String email);
    
    User findByUsername(String username);
}
