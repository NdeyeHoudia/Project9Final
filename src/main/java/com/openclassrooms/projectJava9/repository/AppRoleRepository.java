package com.openclassrooms.projectJava9.repository;

import com.openclassrooms.projectJava9.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);

}
