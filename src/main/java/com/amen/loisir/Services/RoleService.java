package com.amen.loisir.Services;


import com.amen.loisir.Entities.Role;
import com.amen.loisir.Entities.RoleType;
import com.amen.loisir.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public Role addRole(Role r) {

        return roleRepository.save(r);
    }
}
