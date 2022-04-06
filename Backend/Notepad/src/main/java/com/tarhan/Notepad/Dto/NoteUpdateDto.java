package com.tarhan.Notepad.Dto;

import lombok.Data;

@Data
public class NoteUpdateDto {
    private Long noteId;
    private Long userId;
    private String title;
    private String body;
}
