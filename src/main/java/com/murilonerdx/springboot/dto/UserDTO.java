package com.murilonerdx.springboot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.murilonerdx.springboot.entity.Permission;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Transient;
import java.util.Collection;
import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String password;

    public UserDTO(Long id, String username, String fullName, String password) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
