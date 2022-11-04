package com.interfell.usersapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author vbocanegra
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    /**
     * identificador de la tabla user.
     */
    private long userId;

    /**
     * Campo name.
     */
    private String name;

    /**
     * Campo username.
     */
    private String username;

    /**
     * Campo email.
     */
    private String email;

    /**
     * Campo phone.
     */
    private String phone;

    /**
     * mensaje.
     */
    private String mensaje;

    public UserDto() {
    }
    
    
    public UserDto(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Get the value of userId
     *
     * @return the value of userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Set the value of userId
     *
     * @param userId new value of userId
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of phone
     *
     * @return the value of phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set the value of phone
     *
     * @param phone new value of phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Get the value of mensaje
     *
     * @return the value of mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Set the value of mensaje
     *
     * @param mensaje new value of mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}
