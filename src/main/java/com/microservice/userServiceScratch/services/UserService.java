package com.microservice.userServiceScratch.services;

import com.microservice.userServiceScratch.models.Roles;
import com.microservice.userServiceScratch.models.Token;
import com.microservice.userServiceScratch.models.User;
import com.microservice.userServiceScratch.repos.RolesRepo;
import com.microservice.userServiceScratch.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepo userRepo, RolesRepo rolesRepo)
    {
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.userRepo=userRepo;
        this.rolesRepo=rolesRepo;
    }

    public User signUp(String name, String password,List<Roles> roles) {

        for(Roles role:roles) {
        rolesRepo.save(role);}

        User u=User.builder().name(name).roles(roles).hashedPassword( bCryptPasswordEncoder.encode(password)).build();

        userRepo.save(u);
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
