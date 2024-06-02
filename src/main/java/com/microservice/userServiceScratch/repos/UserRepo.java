package com.microservice.userServiceScratch.repos;

import com.microservice.userServiceScratch.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
}
