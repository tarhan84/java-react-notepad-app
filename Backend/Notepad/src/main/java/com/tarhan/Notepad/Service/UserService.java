package com.tarhan.Notepad.Service;

import com.tarhan.Notepad.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(){
        
    }

    public  boolean userIsExist(String username){
        return userRepository.findByUsername(username) != null;
    }

    public boolean usernameFormat(String username){
        if(username == null)
            return false;
        if(username.length()<6)
            return false;
        return true;
    }

    public boolean passwordFormat(String password){
        if(password == null)
            return false;
        if(password.length()<6)
            return false;
        return true;
    }
}
