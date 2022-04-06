package com.tarhan.Notepad.Controller;

import com.tarhan.Notepad.Definitions.ResponseCode;
import com.tarhan.Notepad.Definitions.ResponseDto;
import com.tarhan.Notepad.Dto.NoteDto;
import com.tarhan.Notepad.Service.AuthService;
import com.tarhan.Notepad.Service.NoteService;
import com.tarhan.Notepad.auth.TokenManager;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    AuthService authService;


    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addNote(@RequestBody NoteDto noteDto){
        ResponseDto responseDto = noteService.addNote(noteDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_ADDED)?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/delete")
    public ResponseEntity<ResponseDto> deleteNote(@RequestBody Long noteId){
        ResponseDto responseDto = noteService.deleteNote(noteId);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_ADDED)?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/deletebyuser")
    public ResponseEntity<ResponseDto> deleteByUser(@RequestHeader(value="Authorization") String token){
        Claims claims = authService.getClaims(token.substring(7));
        Long userId = Long.parseLong(claims.getId());
        ResponseDto responseDto = noteService.deleteByUserId(userId);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_DELETED)?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }
}
