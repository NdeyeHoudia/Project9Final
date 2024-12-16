package com.openclassrooms.geteway_service.service;


import com.openclassrooms.geteway_service.model.AppRole;
import com.openclassrooms.geteway_service.model.AppUser;
import com.openclassrooms.geteway_service.repository.AppRoleRepository;
import com.openclassrooms.geteway_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private UserRepository userRepository;
    private AppRoleRepository appRoleRepository;

    private PasswordEncoder passwordEncoder;
    public AccountServiceImpl(UserRepository userRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser addNewUser(AppUser user) {

        String pwd =user.getPassword();
        // encoder avant de stocker dans la bd avec le has
        user.setPassword(passwordEncoder.encode(pwd));
        return userRepository.save(user);
    }

    @Override
    public AppRole addNewRole(AppRole role) {

        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
            AppUser user = userRepository.findByUsername(username);
            AppRole appRole = appRoleRepository.findByRoleName(roleName);

            user.getAppRoles().add(appRole);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {

        return userRepository.findAll();
    }
}
