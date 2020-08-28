package com.volkov.userregsystem.controller;


import com.volkov.userregsystem.dto.UserDTO;
import com.volkov.userregsystem.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {

    public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);

    private UserJpaRepository userJpaRepository;

    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public ResponseEntity<List<UserDTO>> listAllUsers() {
        List<UserDTO> users = userJpaRepository.findAll();
        return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }
}
