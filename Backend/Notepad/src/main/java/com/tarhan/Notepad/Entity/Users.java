package com.tarhan.Notepad.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @TableGenerator(name = "ID_GENERATOR", initialValue = 100000000, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME", unique = true, length = 20, nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

}