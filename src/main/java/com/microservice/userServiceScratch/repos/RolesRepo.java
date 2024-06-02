package com.microservice.userServiceScratch.repos;

import com.microservice.userServiceScratch.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepo extends JpaRepository<Roles, Long> {

}
