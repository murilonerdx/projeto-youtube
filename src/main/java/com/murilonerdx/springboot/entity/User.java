package com.murilonerdx.springboot.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String fullName;
    private String password;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_permission", joinColumns = {
            @JoinColumn(name="id_user")
    },inverseJoinColumns = {
            @JoinColumn(name="id_permission")
    })
    private List<Permission> permisions;

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        for(Permission role : permisions){
            roles.add(role.getDescription());
        }
        return roles;
    }

    public User(Long id, String username, String fullName, String password) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.password = password;
    }

    public User() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
