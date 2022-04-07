package com.tarhan.Notepad.Service;

import com.tarhan.Notepad.Jwt.TokenManager;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    @Autowired
    TokenManager tokenManager;

    public Claims getClaims(String token) {
        return tokenManager.getClaims(token);
    }
}
