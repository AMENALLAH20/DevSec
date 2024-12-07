package com.amen.loisir.Repositories;

import com.amen.loisir.Entities.Role;
import com.amen.loisir.Entities.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByRoleType(RoleType roleType);


}
