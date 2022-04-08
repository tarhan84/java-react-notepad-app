package com.tarhan.Notepad.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "User API", description = "User API")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_GENERATOR")
    @TableGenerator(name = "ID_GENERATOR", initialValue = 100000000, allocationSize = 1)
    @Column(name = "ID")
    @ApiModelProperty(value = "Note Id")
    private Long id;

    @ApiModelProperty(value = "Note Title")
    @Column(name = "TITLE", length = 255)
    private String title;

    @ApiModelProperty(value = "Note Body")
    @Column(name = "BODY", length = 1000)
    private String body;

    @ApiModelProperty(value = "Note Created Date")
    @Column(name = "CREATED_DATE")
    private String createdDate;

    @ApiModelProperty(value = "Note Updated Date")
    @Column(name = "UPDATED_DATE")
    private String updatedDate;

    @ApiModelProperty(value = "Note User's Id")
    @Column(name = "USER_ID", nullable = false)
    private Long userId;
}
