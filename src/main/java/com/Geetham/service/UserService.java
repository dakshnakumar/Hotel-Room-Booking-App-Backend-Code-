package com.Geetham.service;

import com.Geetham.model.Users;
import com.Geetham.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public Users register(Users user){
        return userRepo.save(user);
    }
}
