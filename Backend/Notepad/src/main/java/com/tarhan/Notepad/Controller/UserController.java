package com.tarhan.Notepad.Controller;

import com.sun.istack.Nullable;
import com.tarhan.Notepad.Constants;
import com.tarhan.Notepad.Definitions.ResponseCode;
import com.tarhan.Notepad.Definitions.ResponseDto;
import com.tarhan.Notepad.Dto.UserDto;
import com.tarhan.Notepad.Dto.UserUpdatePassDto;
import com.tarhan.Notepad.Service.AuthService;
import com.tarhan.Notepad.Service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addUser(@RequestBody UserDto userDto, @RequestHeader(value = "Authorization",defaultValue = "none") String token) {
        if(token != null && !token.equals("none") ){
            token = token.substring(7);
            Claims claims = authService.getClaims(token);
            if(claims.get("role").equals(Constants.ROLE_ADMIN)){
                ResponseDto responseDto = userService.addUser(userDto);
                return new ResponseEntity<>(responseDto,
                        responseDto.getResponseCode().equals(ResponseCode.USER_ADDED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
            }
        }
        userDto.setRole("user");
        ResponseDto responseDto = userService.addUser(userDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.USER_ADDED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUser(@RequestBody UserDto userDto) {
        ResponseDto responseDto = userService.deleteUser(userDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.USER_DELETED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/changepassword")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody UserUpdatePassDto userDto) {
        ResponseDto responseDto = userService.changePassword(userDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.USER_UPDATED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


}
