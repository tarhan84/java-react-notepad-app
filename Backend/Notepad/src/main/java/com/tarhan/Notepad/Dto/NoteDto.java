package com.tarhan.Notepad.Dto;

import lombok.Data;

@Data
public class NoteDto {
    private String title;
    private String body;
    private Long userId;
}
