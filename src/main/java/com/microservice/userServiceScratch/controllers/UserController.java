package com.microservice.userServiceScratch.controllers;


import com.microservice.userServiceScratch.dtos.logInDTO;
import com.microservice.userServiceScratch.dtos.signUpDTO;
import com.microservice.userServiceScratch.models.Token;
import com.microservice.userServiceScratch.models.User;
import com.microservice.userServiceScratch.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody signUpDTO dto)
    {
      return userService.signUp(dto.getName(),dto.getHashedPassword(), dto.getRoles(),dto.getEmail());
    }

    @PostMapping("/login")
    public Token login(@RequestBody logInDTO dto)
    {
        return new Token();
    }

    @PostMapping("/validate")
    public void validate(@RequestBody Token t)
    {
        //validate token with its attached user
    }
}
