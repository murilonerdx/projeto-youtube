package com.murilonerdx.springboot.repository;

import com.murilonerdx.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional(readOnly=true)
    User findByUsername(String username);
}
