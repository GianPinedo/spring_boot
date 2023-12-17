package com.blogapp.apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.apis.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
