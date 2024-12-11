package com.openclassrooms.projectJava9.controller;

import com.openclassrooms.projectJava9.model.AppRole;
import com.openclassrooms.projectJava9.model.AppUser;
import com.openclassrooms.projectJava9.service.AccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/users")
    public List<AppUser> appUserList(){
        return accountService.listUsers();
    }

    @PostMapping(path = "/users")
    public AppUser saveUser(@RequestBody AppUser user){
        return  accountService.addNewUser(user);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm){
          accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

}

@Data
class RoleUserForm{
    private String username;
    private String roleName;
}

       /* @RolesAllowed("ROLE_ADMIN")
        @GetMapping("/admin")
        public String admin() {
            return "Hello Admin!";
        }

        @RolesAllowed({ "ROLE_ADMIN", "ROLE_USER" })
        @GetMapping("/user")
        public String user() {
            return "Hello User!";
        }
*/

  /*  @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() { return "index";}
    }
*/