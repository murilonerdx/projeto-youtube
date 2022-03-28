package com.murilonerdx.springboot.controller;

import com.murilonerdx.springboot.dto.CredentialDTO;
import com.murilonerdx.springboot.entity.User;
import com.murilonerdx.springboot.repository.UserRepository;
import com.murilonerdx.springboot.security.JWTTokenProvider;
import com.murilonerdx.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    JWTTokenProvider tokenProvider;

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticantionManager;

    @PostMapping(value="/signin", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> signin(@RequestBody CredentialDTO credentialDTO){
        Map<String, String> model = new HashMap<>();

        try{
            authenticantionManager.authenticate(new UsernamePasswordAuthenticationToken(credentialDTO.getUsername(),
                    credentialDTO.getPassword()));
            String token = "";

            User user = (User) userService.loadUserByUsername(credentialDTO.getUsername());

            if(user != null){
                token = tokenProvider.createToken(user.getUsername(), user.getRoles());
                model.put("username", user.getUsername());
                model.put("token", token);
            }
        }catch(InternalAuthenticationServiceException e){
            throw new InternalAuthenticationServiceException("Username "  + credentialDTO.getUsername() + " not found");
        }

        return ResponseEntity.ok().body(model);
    }
}
