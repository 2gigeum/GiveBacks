package com.example.givebacks.user.repository;

import com.example.givebacks.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserEmail(String email);

    Optional<User> findByUserEmailAndUserPassword(String userEmail, String userPassword);

}
