package com.murilonerdx.springboot.repository;

import com.murilonerdx.springboot.entity.Permission;
import com.murilonerdx.springboot.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByDescriptionEquals(String description);
}
