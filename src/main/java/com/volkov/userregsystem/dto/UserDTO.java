package com.volkov.userregsystem.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data
public class UserDTO {

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;
}
