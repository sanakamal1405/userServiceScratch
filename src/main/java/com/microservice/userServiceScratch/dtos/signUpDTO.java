package com.microservice.userServiceScratch.dtos;

import com.microservice.userServiceScratch.models.Roles;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class signUpDTO {

    String name;
    String hashedPassword;
    List<Roles> roles;
}
