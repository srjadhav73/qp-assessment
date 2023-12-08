package com.grocery.assignment.repositories;

import java.util.HashSet;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.assignment.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	HashSet<Role> findRoleById(int roleId);

}
