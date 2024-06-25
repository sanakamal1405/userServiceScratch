package com.microservice.userServiceScratch.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.userServiceScratch.dtos.NotificationDTO;
import com.microservice.userServiceScratch.models.Roles;
import com.microservice.userServiceScratch.models.Token;
import com.microservice.userServiceScratch.models.User;
import com.microservice.userServiceScratch.repos.RolesRepo;
import com.microservice.userServiceScratch.repos.UserRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepo userRepo;
    private RolesRepo rolesRepo;
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepo userRepo, RolesRepo rolesRepo,KafkaTemplate<String, String> kafkaTemplate,ObjectMapper objectMapper)
    {
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.userRepo=userRepo;
        this.rolesRepo=rolesRepo;
        this.kafkaTemplate=kafkaTemplate;
        this.objectMapper=objectMapper;
    }

    @SneakyThrows
    public User signUp(String name, String password, List<Roles> roles, String email) {

        for(Roles role:roles) {
        rolesRepo.save(role);}

        User u=User.builder().name(name).roles(roles).hashedPassword( bCryptPasswordEncoder.encode(password)).build();

        userRepo.save(u);

        NotificationDTO notificationDTO= NotificationDTO.builder()
                .to(email)
                .from("kamalsana5041@gmail.com")
                .body("Welcome to Ecommerce Application!")
                .subject("Sign Up Alert")
                .build();

        kafkaTemplate.send("sendEmail", objectMapper.writeValueAsString(notificationDTO));


        return u;
    }

    public Token signIn(String username, String password) {


        Optional<User> u=userRepo.findByName(username);
        if(u.get()==null)
            throw new UsernameNotFoundException("User not found");
        if(bCryptPasswordEncoder.matches(password, u.get().getHashedPassword()))
        return new Token();
        throw new UsernameNotFoundException("Wrong credentials");
    }
}
