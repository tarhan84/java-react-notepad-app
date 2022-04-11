package com.tarhan.Notepad.Controller;

import com.sun.istack.Nullable;
import com.tarhan.Notepad.Constants;
import com.tarhan.Notepad.Definitions.ResponseCode;
import com.tarhan.Notepad.Definitions.ResponseDto;
import com.tarhan.Notepad.Dto.UserDto;
import com.tarhan.Notepad.Dto.UserUpdatePassDto;
import com.tarhan.Notepad.Jwt.TokenManager;
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

    @Autowired
    TokenManager tokenManager;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addUser(@RequestBody UserDto userDto, @RequestHeader(value = "Authorization",defaultValue = "none") String token) {
        try {
            if(token != null && !token.equals("none") ){
                token = token.substring(7);
                Claims claims = tokenManager.getClaims(token);
                if(claims.get("role").equals(Constants.ROLE_ADMIN)){
                    ResponseDto responseDto = userService.addUser(userDto);
                    return new ResponseEntity<>(responseDto,
                            responseDto.getResponseCode().equals(ResponseCode.USER_ADDED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e){

        }
        userDto.setRole("user");
        ResponseDto responseDto = userService.addUser(userDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.USER_ADDED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUser(@RequestBody String username,@RequestHeader(value = "Authorization",defaultValue = "none") String token) {
        Claims claims = authService.getClaims(token.substring(7));
        String token_username = claims.getSubject();
        String role = (String) claims.get("role");
        if(!username.equals(token_username)){
            if(!role.equals(Constants.ROLE_ADMIN)){
                return new ResponseEntity<>(new ResponseDto(ResponseCode.USER_DELETE_ERROR,"you are not authorized"),
                        HttpStatus.BAD_REQUEST);
            }
        }
        ResponseDto responseDto = userService.deleteUser(username);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.USER_DELETED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/changepassword")
    public ResponseEntity<ResponseDto> changePassword(@RequestBody UserUpdatePassDto userDto,@RequestHeader(value = "Authorization",defaultValue = "none") String token) {
        ResponseDto responseDto = userService.changePassword(userDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.USER_UPDATED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


}
