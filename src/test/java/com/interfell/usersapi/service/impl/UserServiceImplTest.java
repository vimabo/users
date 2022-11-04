/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfell.usersapi.service.impl;

import com.interfell.usersapi.dao.UserDao;
import com.interfell.usersapi.dto.UserDto;
import com.interfell.usersapi.modelo.User;
import io.swagger.models.HttpMethod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Prueba unitaria UserServiceImpl.
 *
 * @author Victor Bocanegra
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserDao userDao;

    @Mock
    RestTemplate restTemplate;

    @Test
    @DisplayName("Operacion de listar Usuarios.")
    public void listarUsers() {

        User user = new User();
        user.setName("Victor Manuel");
        user.setUsername("vimabo");
        user.setEmail("gggg@gmail.com");
        user.setPhone("565646456");
        List<User> listUsers = new ArrayList<>();
        listUsers.add(user);
        when(userDao.findAll()).thenReturn(listUsers);
        assertTrue(userService.getUsers().size() > 0);

    }

    @Test
    @DisplayName("Operacion de listar Usuarios vacia.")
    public void listarUsersEmpty() {
        List<User> listUsers = new ArrayList<>();
        when(userDao.findAll()).thenReturn(listUsers);
        assertTrue(userService.getUsers().isEmpty());

    }

    @Test
    @DisplayName("Operacion de guardar Usuario.")
    public void guardarUser() {

        User user = new User();
        user.setUserId(1);
        user.setName("Victor");
        user.setUsername("boca");
        UserDto dto = new UserDto();
        dto.setName("Victor");
        dto.setUsername("boca");
        when(userDao.save(Mockito.any(User.class))).thenReturn(user);
        assertTrue(userService.createUser(dto).getMensaje().contains("Usuario creado satisfatoriamente"));
    }

    @Test
    @DisplayName("Operacion de guardar Usuario campos obligatorios.")
    public void guardarUserCamposObligatorios() {
        UserDto dto = new UserDto();
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(dto);
        });
        assertEquals("El nombre y el usuario son obligatorios", exception.getMessage());
    }

    @Test
    @DisplayName("Operacion de guardar Usuario ya existe usuario.")
    public void guardarUserYaExisteUsuario() {
        User user = new User();
        user.setUserId(1);
        user.setName("Victor");
        user.setUsername("boca");
        UserDto dto = new UserDto();
        dto.setName("Victor");
        dto.setUsername("boca");
        when(userDao.findByUsername("boca")).thenReturn(user);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(dto);
        });
        assertEquals("Ya existe un usuario con el username: boca", exception.getMessage());
    }

    @Test
    @DisplayName("Operacion de guardar Usuario Error.")
    public void guardarUserError() {
        User user = new User();
        user.setUserId(1);
        user.setName("Victor");
        user.setUsername("boca");
        UserDto dto = new UserDto();
        dto.setName("Victor");
        dto.setUsername("boca");
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(dto);
        });
        assertEquals("Ocurrio un error al momento de crear el usuario", exception.getMessage());
    }

    @Test
    @DisplayName("Operacion Encontrar usuario por email.")
    public void findByEmail() {
        String email = "bvbvbv@gmail.com";
        User user = new User();
        user.setName("Victor");
        user.setUsername("boca");
        List<User> lst = new ArrayList<>();
        lst.add(user);
        when(userDao.findByEmail(email)).thenReturn(lst);
        assertTrue(userService.findByEmail(email).getName().contains(user.getName()));
    }

    @Test
    @DisplayName("Operacion Encontrar usuario por email usuario no existe.")
    public void findByEmailUserNotExist() {
        String email = "bvbvbv@gmail.com";
        assertTrue(userService.findByEmail(email).getMensaje().contains("No existe usuario con la informaciòn ingresada"));
    }

    @Test
    @DisplayName("Operacion borrar usuario.")
    public void deleteUser() {

        User user = new User();
        user.setUserId(1);
        user.setName("Victor");
        user.setUsername("boca");
        assertTrue(userService.deleteUser(user.getUserId()).getMensaje().contains("Usuario Eliminado satisfatoriamente"));
    }

    @Test
    @DisplayName("Operacion consultarApiExternaOK.")
    public void consultarApiExternaOK() {
        assertTrue(this.userService.consultarApiExterna("1-9").
                getDescription().equalsIgnoreCase("OK"));

    }

    @Test
    @DisplayName("Operacion consultarApiExternaParametroError.")
    public void consultarApiExternaParametroError() {
        assertTrue(this.userService.consultarApiExterna("1-5").
                getResponseCode() == 400);
    }

}
