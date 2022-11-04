package com.interfell.usersapi.controllers;

import com.interfell.usersapi.dto.ResponseApiExternaDto;
import com.interfell.usersapi.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.interfell.usersapi.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Metodo que permite obtener todos los usuarios de la BD.
     *
     * @author Victor Bocanegra
     * @return List UserDto
     */
    @RequestMapping(value = "/api/1.0/users/list",
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<UserDto>> findAll() {
        try {
            List<UserDto> list = userService.getUsers();
            return ResponseEntity.ok(list);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ArrayList<>(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Metodo que permite obtener todos los usuarios de la BD.
     *
     * @author Victor Bocanegra
     * @param email STring
     * @return UserDto
     */
    @RequestMapping(value = "/api/1.0/users/findByEmail/{email}",
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<UserDto> findByEmail(@PathVariable(value = "email") String email) {
        UserDto user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    /**
     * Metodo que permite la creacion de un usuario.
     *
     * @author Victor Bocanegra
     * @param request UserDto
     * @return UserDto
     */
    @RequestMapping(value = "/api/1.0/users/create",
            method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<UserDto> create(@RequestBody UserDto request) {
        try {
            request = userService.createUser(request);
            return ResponseEntity.ok(request);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(new UserDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new UserDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Metodo que permite la eliminacion de un usuario en BD.
     *
     * @author Victor Bocanegra
     * @param userId Long
     * @return UserDto
     */
    @RequestMapping(value = "/api/1.0/users/delete/{userId}",
            method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<UserDto> delete(@PathVariable("userId") long userId) {
        try {
            UserDto delete = userService.deleteUser(userId);
            return ResponseEntity.ok(delete);
        } catch (EmptyResultDataAccessException ex) {
            return new ResponseEntity<>(new UserDto(ex.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new UserDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     /**
     * Metodo que permite consultar una api externa
     *
     * @author Victor Bocanegra
     * @param param
     * @return ResponseApiExternaDto
     */
    @RequestMapping(value = "/api/1.0/users/externa", method = RequestMethod.POST)
    public ResponseApiExternaDto consultarApiExterna(@RequestParam String param) {     
        return userService.consultarApiExterna(param);
    }
}
