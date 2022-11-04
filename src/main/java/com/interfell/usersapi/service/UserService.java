/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfell.usersapi.service;

import com.interfell.usersapi.dto.ResponseApiExternaDto;
import com.interfell.usersapi.dto.UserDto;
import java.util.List;

/**
 *
 * @author vbocanegra
 */
public interface UserService {

    /**
     * Metodo que permite la creacion de un usuario.
     *
     * @author Victor Bocanegra
     * @param user UserDto
     * @return UserDto
     */
    UserDto createUser(UserDto user);

    /**
     * Metodo que retorna listado de todos los usuarios en BD.
     *
     * @author Victor Bocanegra
     * @return List UserDto
     */
    List<UserDto> getUsers();

    /**
     * Metodo que permite la consulta de un usuario por el email.
     *
     * @author Victor Bocanegra
     * @param email String
     * @return UserDto
     */
    UserDto findByEmail(String email);

    /**
     * Metodo que permite la eliminacion de un usuario en BD.
     *
     * @author Victor Bocanegra
     * @param idUser Long
     * @return UserDto
     */
    UserDto deleteUser(Long idUser);
    
    /**
     * Metodo que permite la eliminacion de un usuario en BD.
     *
     * @author Victor Bocanegra
     * @param param String
     * @return ResponseApiExternaDto
     */
    ResponseApiExternaDto consultarApiExterna(String param);
}
