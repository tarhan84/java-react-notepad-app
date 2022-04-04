package com.tarhan.Notepad.Repository;

import com.tarhan.Notepad.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    @Query("SELECT usr FROM Users usr WHERE usr.userName = :userName")
    public Users findByUsername(@Param("userName") String userName);
}
