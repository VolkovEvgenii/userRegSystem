package com.volkov.userregsystem.repository;

import com.volkov.userregsystem.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findByName(String name);
    void deleteById(Long id);
}
