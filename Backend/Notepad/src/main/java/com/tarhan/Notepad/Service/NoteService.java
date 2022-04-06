package com.tarhan.Notepad.Service;

import com.tarhan.Notepad.Definitions.ResponseCode;
import com.tarhan.Notepad.Definitions.ResponseDto;
import com.tarhan.Notepad.Dto.NoteDto;
import com.tarhan.Notepad.Entity.Notes;
import com.tarhan.Notepad.Entity.Users;
import com.tarhan.Notepad.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Component
@Transactional(rollbackFor = Exception.class)
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    UserService userService;

    public ResponseDto addNote(NoteDto noteDto) {
        Optional<Users> user = userService.findById(noteDto.getUserId());
        if (user.isEmpty())
            return new ResponseDto(ResponseCode.USER_NOT_FOUND, "user not found");

        if (!checkNoteFormat(noteDto))
            return new ResponseDto(ResponseCode.NOTE_FORMAT_ERROR, "body and title not be empty");

        try {
            Notes note = new Notes();
            note.setTitle(noteDto.getTitle());
            note.setBody(noteDto.getBody());
            note.setUserId(noteDto.getUserId());
            note.setCreatedDate(getDate());
            noteRepository.save(note);
            return new ResponseDto(ResponseCode.NOTE_ADDED, "note added");
        } catch (Exception e) {
            return new ResponseDto(ResponseCode.NOTE_ERROR, e.toString());
        }
    }

    public ResponseDto deleteNote(Long noteId) {
        try {
            noteRepository.deleteById(noteId);
            return new ResponseDto(ResponseCode.NOTE_DELETED, "note deleted");
        } catch (Exception e) {
            return new ResponseDto(ResponseCode.NOTE_ERROR, "note delete error");
        }
    }

    public ResponseDto deleteByUserId(Long userId) {
        noteRepository.deleteByUserId(userId);
        return new ResponseDto(ResponseCode.NOTE_DELETED, "notes are deleted");
    }

    public boolean checkNoteFormat(NoteDto noteDto) {
        String body = noteDto.getBody();
        String title = noteDto.getTitle();
        return body != null && body != "" && title != null && title != "";
    }

    public String getDate() {
        String pattern = "yyyy/MM/dd-hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        return date;
    }


}
