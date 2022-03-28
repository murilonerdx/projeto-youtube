package com.murilonerdx.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="tb_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String fullName;
    private String password;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_permission", joinColumns = {
            @JoinColumn(name="id_user")
    },inverseJoinColumns = {
            @JoinColumn(name="id_permission")
    })
    private List<Permission> permisions = new ArrayList<>();

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
        this.authorities = getRoles().stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return password;
    }

    public List<Permission> getPermisions() {
        return permisions;
    }

    public void setPermisions(List<Permission> permisions) {
        this.permisions = permisions;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
