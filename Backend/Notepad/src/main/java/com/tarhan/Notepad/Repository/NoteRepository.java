package com.tarhan.Notepad.Repository;

import com.tarhan.Notepad.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Notes,Long> {
    int deleteByUserId(Long userId);

}
