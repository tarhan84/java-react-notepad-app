package com.tarhan.Notepad.Dto;

import lombok.Data;

@Data
public class UserUpdatePassDto {
    private String username;
    private String oldPassword;
    private String newPassword;
}
