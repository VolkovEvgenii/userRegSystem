package com.volkov.userregsystem.controller;


import com.volkov.userregsystem.dto.UserDTO;
import com.volkov.userregsystem.exception.CustomErrorType;
import com.volkov.userregsystem.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRegistrationRestController {

    public static final Logger logger = LoggerFactory.getLogger(UserRegistrationRestController.class);

    private UserJpaRepository userJpaRepository;

    @Autowired
    public void setUserJpaRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> listAllUsers() {
        List<UserDTO> users = userJpaRepository.findAll();
        if(users.isEmpty()) {
            return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserbyId(@PathVariable("id") final Long id) {
        UserDTO user = userJpaRepository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<UserDTO>(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody final UserDTO user) {
        if (userJpaRepository.findByName(user.getName()) != null) {
            return new ResponseEntity<UserDTO>(new CustomErrorType(
                    "Unable to create new user. The user with name " + user.getName() + " already exist."),
                    HttpStatus.CONFLICT);
        }
        userJpaRepository.save(user);
        return new ResponseEntity<UserDTO>(user, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") final Long id, @RequestBody UserDTO user) {

        UserDTO currentUser = userJpaRepository.findById(id).orElse(null);
        if (currentUser == null) {
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType("Unable to update. User with id " + id + " not found"), HttpStatus.NOT_FOUND
            );
        }
        currentUser.setName(user.getName());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());

        userJpaRepository.saveAndFlush(currentUser);

        return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") final Long id) {
        UserDTO user = userJpaRepository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<UserDTO>(
                    new CustomErrorType("Unable to delete. User with id " + id + " not found"), HttpStatus.NOT_FOUND
            );
        }
        userJpaRepository.deleteById(id);
        return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
    }


}
