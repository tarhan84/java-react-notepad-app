package com.tarhan.Notepad.Controller;

import com.tarhan.Notepad.Dto.UserDto;
import com.tarhan.Notepad.Entity.Notes;
import com.tarhan.Notepad.Entity.Users;
import com.tarhan.Notepad.Repository.NoteRepository;
import com.tarhan.Notepad.Repository.UserRepository;
import com.tarhan.Notepad.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/add")
    public void addUser(@RequestBody UserDto userDto){
        userService.addUser();
    }


}
