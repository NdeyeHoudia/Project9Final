package com.openclassrooms.geteway_service.service;



import com.openclassrooms.geteway_service.model.AppRole;
import com.openclassrooms.geteway_service.model.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser user);
    AppRole addNewRole(AppRole role);
    // affecter un role à un user
    void addRoleToUser(String username, String roleName);
   // retouner un user
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
