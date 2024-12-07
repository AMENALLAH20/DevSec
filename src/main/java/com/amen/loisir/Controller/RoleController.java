package com.amen.loisir.Controller;


import com.amen.loisir.Entities.Role;
import com.amen.loisir.Entities.RoleType;
import com.amen.loisir.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("role")
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping("/add")
    public Role RoleaddRole(@RequestBody Role r) {

        return roleService.addRole(r);
    }
}
