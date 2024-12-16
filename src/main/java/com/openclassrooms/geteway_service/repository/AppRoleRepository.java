package com.openclassrooms.geteway_service.repository;


import com.openclassrooms.geteway_service.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);

}
