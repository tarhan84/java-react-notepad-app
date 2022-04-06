package com.tarhan.Notepad.Service;

import com.tarhan.Notepad.Definitions.ResponseCode;
import com.tarhan.Notepad.Definitions.ResponseDto;
import com.tarhan.Notepad.Dto.UserDto;
import com.tarhan.Notepad.Dto.UserUpdatePassDto;
import com.tarhan.Notepad.Entity.Users;
import com.tarhan.Notepad.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private Users user;

    public ResponseDto addUser(UserDto usd) {
        String userName = usd.getUsername();
        String password = usd.getPassword();

        if (userIsExist(userName))
            return new ResponseDto(ResponseCode.USER_EXIST, "user exist");

        if (!usernameFormat(userName))
            return new ResponseDto(ResponseCode.INVALID_USERNAME_FORMAT, "invalid username format");

        if (!passwordFormat(password))
            return new ResponseDto(ResponseCode.INVALID_PASSWORD_FORMAT, "invalid password format");

        Users user = new Users();
        user.setUserName(userName);
        user.setPassword(password);

        try {
            userRepository.save(user);
            return new ResponseDto(ResponseCode.USER_ADDED, "user added");
        } catch (Exception e) {
            return new ResponseDto(ResponseCode.USER_ADD_ERROR, e.toString());
        }
    }

    public ResponseDto deleteUser(UserDto usd) {
        String username = usd.getUsername();
        String password = usd.getPassword();

        if (!userIsExist(username)) {
            return new ResponseDto(ResponseCode.USER_NOT_FOUND, "user not found");
        }

        Users user = userRepository.findByUsername(username);

        if (!user.getPassword().equals(password)) {
            return new ResponseDto(ResponseCode.PASSWORD_ERROR, "wrong password");
        }

        try {
            userRepository.delete(user);
            return new ResponseDto(ResponseCode.USER_DELETED, "user deleted");
        } catch (Exception e) {
            return new ResponseDto(ResponseCode.USER_DELETE_ERROR, "user deleted error");
        }

    }

    public ResponseDto changePassword(UserUpdatePassDto usd){
        String username = usd.getUsername();
        String oldPassword = usd.getOldPassword();
        String newPassword = usd.getNewPassword();

        if(!userIsExist(username))
            return new ResponseDto(ResponseCode.USER_NOT_FOUND, "user not found");

        Users user = userRepository.findByUsername(username);

        if(!user.getPassword().equals(oldPassword))
            return new ResponseDto(ResponseCode.PASSWORD_ERROR, "wrong password");

        if(!passwordFormat(newPassword))
            return new ResponseDto(ResponseCode.INVALID_PASSWORD_FORMAT, "invalid password format");

        if(oldPassword.equals(newPassword))
            return new ResponseDto(ResponseCode.INVALID_PASSWORD_FORMAT, "old password and new password are same");

        try{
            user.setPassword(newPassword);
            userRepository.save(user);
            return new ResponseDto(ResponseCode.USER_UPDATED, "password updated");
        }catch (Exception e){
            return new ResponseDto(ResponseCode.USER_UPDATE_ERROR, e.toString());
        }
    }

    public boolean userIsExist(String username) {
        return userRepository.findByUsername(username) != null;
    }

    public boolean usernameFormat(String username) {
        if (username == null)
            return false;
        if (username.length() < 6)
            return false;
        return true;
    }

    public boolean passwordFormat(String password) {
        if (password == null)
            return false;
        if (password.length() < 6)
            return false;
        return true;
    }

    public Optional<Users> findById(Long userId){
      return userRepository.findById(userId);
    }
    public  Users findByUsername(String username){ return userRepository.findByUsername(username);}

}
