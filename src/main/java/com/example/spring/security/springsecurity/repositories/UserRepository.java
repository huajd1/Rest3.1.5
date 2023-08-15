package com.example.spring.security.springsecurity.repositories;

import com.example.spring.security.springsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName (String username);
    @Override
    Optional<User> findById(Long id);
}
