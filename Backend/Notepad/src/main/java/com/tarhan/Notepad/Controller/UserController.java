package com.tarhan.Notepad.Controller;

import com.tarhan.Notepad.Definitions.ResponseCode;
import com.tarhan.Notepad.Definitions.ResponseDto;
import com.tarhan.Notepad.Dto.UserDto;
import com.tarhan.Notepad.Entity.Notes;
import com.tarhan.Notepad.Entity.Users;
import com.tarhan.Notepad.Repository.NoteRepository;
import com.tarhan.Notepad.Repository.UserRepository;
import com.tarhan.Notepad.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/add")
    public ResponseEntity<ResponseDto> addUser(@RequestBody UserDto userDto){
        ResponseDto responseDto = userService.addUser(userDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.USER_ADDED)?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @PostMapping("user/delete")
    public ResponseEntity<ResponseDto> deleteUser(@RequestBody UserDto userDto){
        ResponseDto responseDto = userService.deleteUser(userDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.USER_DELETED)?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }


}
