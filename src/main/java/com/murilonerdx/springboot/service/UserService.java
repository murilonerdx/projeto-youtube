package com.murilonerdx.springboot.service;

import com.murilonerdx.springboot.dto.UserDTO;
import com.murilonerdx.springboot.entity.Permission;
import com.murilonerdx.springboot.entity.User;
import com.murilonerdx.springboot.repository.PermissionRepository;
import com.murilonerdx.springboot.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }

    public UserDTO create(UserDTO userDTO){
        Permission role = permissionRepository.findByDescriptionEquals("ROLE_USER");

        UserDTO userDTOCreate = new UserDTO();
        User user = new User();
        user.setId(null);
        user.setFullName(userDTO.getFullName());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        user.getPermisions().add(role);
        user.setUsername(userDTO.getUsername());

        User save = repository.save(user);

        BeanUtils.copyProperties(save, userDTOCreate);
        return userDTOCreate;
    }
}
