package com.interfell.usersapi.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vbocanegra
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    /**
     * identificador de la tabla user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false, nullable = false)
    private long userId;

    /**
     * Campo name.
     */
    @Column(name = "name")
    private String name;

    /**
     * Campo username.
     */
    @Column(name = "username")
    private String username;

    /**
     * Campo email.
     */
    @Column(name = "email")
    private String email;

    /**
     * Campo phone.
     */
    @Column(name = "phone")
    private String phone;

    public User() {
    }

    public User(String name,
            String username, String email,
            String phone) {
        super();
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
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

}
