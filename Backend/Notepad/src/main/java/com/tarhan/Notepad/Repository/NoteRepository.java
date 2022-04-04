package com.tarhan.Notepad.Repository;

import com.tarhan.Notepad.Entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Notes,Long> {
}
