package com.pfe.campagne.repository;

import com.pfe.campagne.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RoleRepository extends JpaRepository<Role,Integer> {

    public Role findByRoleName(String roleName);

}
