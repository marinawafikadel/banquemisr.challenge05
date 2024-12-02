package com.example.banquemisr.challenge05.Repositories;

import com.example.banquemisr.challenge05.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}



