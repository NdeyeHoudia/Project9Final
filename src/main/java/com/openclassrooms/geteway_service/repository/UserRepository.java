package com.openclassrooms.geteway_service.repository;

import com.openclassrooms.geteway_service.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

}
