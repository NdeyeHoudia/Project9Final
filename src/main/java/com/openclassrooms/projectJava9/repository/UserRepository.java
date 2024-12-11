package com.openclassrooms.projectJava9.repository;

import com.openclassrooms.projectJava9.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

}
