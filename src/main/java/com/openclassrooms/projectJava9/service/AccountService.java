package com.openclassrooms.projectJava9.service;

import com.openclassrooms.projectJava9.model.AppRole;
import com.openclassrooms.projectJava9.model.AppUser;

import java.util.List;

public interface AccountService {
    AppUser  addNewUser(AppUser user);
    AppRole addNewRole(AppRole role);
    // affecter un role à un user
    void addRoleToUser(String username, String roleName);
   // retouner un user
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
