package com.tarhan.Notepad.Controller;

import com.tarhan.Notepad.Definitions.ResponseCode;
import com.tarhan.Notepad.Definitions.ResponseDto;
import com.tarhan.Notepad.Dto.NoteDto;
import com.tarhan.Notepad.Dto.NoteUpdateDto;
import com.tarhan.Notepad.Service.AuthService;
import com.tarhan.Notepad.Service.NoteService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
@Api(value = "API Controller")
public class NoteController {

    @Autowired
    NoteService noteService;

    @Autowired
    AuthService authService;


    @PostMapping("/add")
    @ApiOperation("Add Note")
    public ResponseEntity<ResponseDto> add(@RequestBody NoteDto noteDto) {
        ResponseDto responseDto = noteService.addNote(noteDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_ADDED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/delete")
    @ApiOperation("Delete Note")
    public ResponseEntity<ResponseDto> deleteNote(@RequestParam("noteId") Long noteId) {
        ResponseDto responseDto = noteService.deleteNote(noteId);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_ADDED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deletebyuser")
    @ApiOperation("Delete Note By User Id")
    public ResponseEntity<ResponseDto> deleteByUser(@RequestHeader(value = "Authorization") String token) {
        Claims claims = authService.getClaims(token.substring(7));
        Long userId = Long.parseLong(claims.getId());
        ResponseDto responseDto = noteService.deleteByUserId(userId);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_DELETED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/update")
    @ApiOperation("Update Note")
    public ResponseEntity<ResponseDto> update(@RequestBody NoteUpdateDto noteUpdateDto) {
        ResponseDto responseDto = noteService.update(noteUpdateDto);
        return new ResponseEntity<>(responseDto,
                responseDto.getResponseCode().equals(ResponseCode.NOTE_UPDATED) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
