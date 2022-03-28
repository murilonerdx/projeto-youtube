package com.murilonerdx.springboot.service;

import com.murilonerdx.springboot.entity.Permission;
import com.murilonerdx.springboot.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    @Autowired
    private PermissionRepository permissionRepository;

    public void instantiateTestDatabase() {
        permissionRepository.save(new Permission(null, "ROLE_USER"));
        permissionRepository.save(new Permission(null, "ROLE_ADMIN"));
    }
}
