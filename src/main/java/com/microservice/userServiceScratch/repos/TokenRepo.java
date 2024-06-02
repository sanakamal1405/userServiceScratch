package com.microservice.userServiceScratch.repos;

import com.microservice.userServiceScratch.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, Long> {
}
