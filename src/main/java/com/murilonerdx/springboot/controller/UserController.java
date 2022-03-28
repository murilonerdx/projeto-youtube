package com.murilonerdx.springboot.controller;

import com.murilonerdx.springboot.dto.CredentialDTO;
import com.murilonerdx.springboot.dto.UserDTO;
import com.murilonerdx.springboot.entity.User;
import com.murilonerdx.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO){
        return ResponseEntity.ok().body(userService.create(userDTO));
    }
}
