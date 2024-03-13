package com.blogapp.apis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.apis.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
