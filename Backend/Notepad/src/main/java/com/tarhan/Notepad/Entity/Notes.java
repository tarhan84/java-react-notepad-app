package com.tarhan.Notepad.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "NOTES")
@Getter
@Setter
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "ID_GENERATOR")
    @TableGenerator(name = "ID_GENERATOR", initialValue = 100000000,allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE", length = 255)
    private String title;

    @Column(name = "BODY", length = 1000)
    private String body;

    @Column(name = "CREATED_DATE")
    private String createdDate;

    @Column(name = "UPDATED_DATE")
    private String updatedDate;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;
}
