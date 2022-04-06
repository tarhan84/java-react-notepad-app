package com.tarhan.Notepad.Controller;

import com.tarhan.Notepad.Definitions.ResponseCode;
import com.tarhan.Notepad.Definitions.ResponseDto;
import com.tarhan.Notepad.Dto.NoteDto;
import com.tarhan.Notepad.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    @PostMapping("/note/add")
    public ResponseEntity<ResponseDto> addNote(@RequestBody NoteDto noteDto){
        ResponseDto responseDto = noteService.addNote(noteDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_ADDED)?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/note/delete")
    public ResponseEntity<ResponseDto> deleteNote(@RequestBody Long noteId){
        ResponseDto responseDto = noteService.deleteNote(noteId);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_ADDED)?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/note/deletebyuser")
    public ResponseEntity<ResponseDto> deleteByUser(@RequestBody Long userId){
        ResponseDto responseDto = noteService.deleteByUserId(userId);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_DELETED)?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }
}
