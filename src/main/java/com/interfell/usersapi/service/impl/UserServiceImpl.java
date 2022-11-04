/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.interfell.usersapi.service.impl;

import com.interfell.usersapi.dao.UserDao;
import com.interfell.usersapi.dto.ResponseApiExternaDto;
import com.interfell.usersapi.dto.ResponseApiExternaDto.Result;
import com.interfell.usersapi.dto.UserDto;
import com.interfell.usersapi.modelo.User;
import com.interfell.usersapi.service.UserService;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author vbocanegra
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final String HEADER_KEY = "f2f719e0";

    private String URL_API_EXTERNA = "https://my.api.mockaroo.com/test-tecnico/search/";

    /**
     * Metodo que permite la creacion de un usuario.
     *
     * @author Victor Bocanegra
     * @param user UserDto
     * @return UserDto
     */
    @Override
    public UserDto createUser(UserDto user) {
        UserDto result = null;
        if (user.getName() != null && user.getUsername() != null) {
            User consulta = userDao.findByUsername(user.getUsername());
            if (consulta == null) {
                User entity = new User(user.getName(), user.getUsername(),
                        user.getEmail(), user.getPhone());
                entity = userDao.save(entity);
                if (entity != null) {
                    result = new UserDto();
                    result.setUserId(entity.getUserId());
                    result.setName(entity.getName());
                    result.setUsername(entity.getUsername());
                    result.setEmail(entity.getEmail());
                    result.setPhone(entity.getPhone());
                    result.setMensaje("Usuario creado satisfatoriamente");
                } else {
                    throw new IllegalArgumentException("Ocurrio un error al momento de crear el usuario");
                }
            } else {
                throw new IllegalArgumentException("Ya existe un usuario con el username: " + user.getUsername() + "");
            }
        } else {
            throw new IllegalArgumentException("El nombre y el usuario son obligatorios");
        }
        return result;
    }

    /**
     * Metodo que retorna listado de todos los usuarios en BD.
     *
     * @author Victor Bocanegra
     * @return List UserDto
     */
    @Override
    public List<UserDto> getUsers() {

        List<User> list = userDao.findAll();
        List<UserDto> result = new ArrayList<>();
        for (User user : list) {
            UserDto dto = new UserDto();
            dto.setUserId(user.getUserId());
            dto.setName(user.getName());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            result.add(dto);
        }
        return result;
    }

    /**
     * Metodo que permite la consulta de un usuario por el email.
     *
     * @author Victor Bocanegra
     * @param email String
     * @return UserDto
     */
    @Override
    public UserDto findByEmail(String email) {
        List<User> users = userDao.findByEmail(email);
        UserDto result = new UserDto();
        if (users != null && !users.isEmpty()) {
            result.setUserId(users.get(0).getUserId());
            result.setName(users.get(0).getName());
            result.setUsername(users.get(0).getUsername());
            result.setEmail(users.get(0).getEmail());
            result.setPhone(users.get(0).getPhone());
        } else {
            result.setMensaje("No existe usuario con la informaciòn ingresada");
        }
        return result;
    }

    /**
     * Metodo que permite la eliminacion de un usuario en BD.
     *
     * @author Victor Bocanegra
     * @param idUser Long
     * @return UserDto
     */
    @Override
    public UserDto deleteUser(Long idUser) {
        UserDto result = new UserDto();
        try {
            userDao.deleteById(idUser);
            result.setMensaje("Usuario Eliminado satisfatoriamente");
        } catch (EmptyResultDataAccessException ex) {
            result.setMensaje("No existe usuario con la informaciòn ingresada");
        }
        return result;
    }

    /**
     * Metodo que permite la eliminacion de un usuario en BD.
     *
     * @author Victor Bocanegra
     * @param param String
     * @return ResponseApiExternaDto
     */
    @Override
    public ResponseApiExternaDto consultarApiExterna(String param) {
        
        ResponseApiExternaDto respuesta = null;
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            String cifrado = cifradoDES(param);
            
            String uri = URL_API_EXTERNA + cifrado;
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("X-API-Key", HEADER_KEY);
            
            HttpEntity<String> entity = new HttpEntity<>(headers);
            
            long startTime = System.currentTimeMillis();
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<String>() {
            });
            long duration = System.currentTimeMillis() - startTime;
            
            String response = responseEntity.getBody();
            
            if (response != null && !response.isEmpty()) {
                JSONParser parser = new JSONParser();
                JSONObject json = (JSONObject) parser.parse(response);
                if (json != null) {
                    respuesta = new ResponseApiExternaDto();
                    respuesta.setElapsedTime(duration);
                    if (json.containsKey("responseCode")) {
                        respuesta.setResponseCode((long) json.get("responseCode"));
                    }
                    if (json.containsKey("description")) {
                        respuesta.setDescription((String) json.get("description"));
                    }
                    
                    if (json.containsKey("result")) {
                        JSONObject result = (JSONObject) json.get("result");
                        if (result.containsKey("items")) {
                            JSONArray listItems = (JSONArray) result.get("items");
                            Result res = respuesta.new Result(listItems.size());
                            respuesta.setResult(res);
                        }
                    }
                }
            }
        } catch (ParseException | IllegalArgumentException| 
                RestClientException ex) {
            respuesta = new ResponseApiExternaDto();
            respuesta.setResponseCode(400);
            respuesta.setDescription(ex.getMessage());
        }
        return respuesta;
    }

    /**
     * Metodo que permite el cifrado de un texto en DES.
     *
     * @author Victor Bocanegra
     * @param texto String
     * @return String
     */
    public String cifradoDES(String texto) {
        try {

            SecureRandom secureRandom = new SecureRandom();
            DESKeySpec keySpec = new DESKeySpec("ionix123456".getBytes("UTF8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(keySpec);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, secureRandom);
            byte[] bytes = cipher.doFinal(texto.getBytes("UTF8"));
            String cifrado = Base64.getEncoder().encodeToString(bytes);
            return cifrado;

        } catch (UnsupportedEncodingException
                | IllegalBlockSizeException | BadPaddingException
                | InvalidKeyException | NoSuchAlgorithmException
                | NoSuchPaddingException | InvalidKeySpecException ex) {
            throw new IllegalArgumentException("Ocurrio un error al momento de cifrar el parametro");
        }
    }

}
