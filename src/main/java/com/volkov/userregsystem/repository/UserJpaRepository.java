package com.volkov.userregsystem.repository;

import com.volkov.userregsystem.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findByName(String name);
}
