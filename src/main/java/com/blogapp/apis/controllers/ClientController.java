package com.blogapp.apis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.blogapp.apis.payload.ClientDto;
import com.blogapp.apis.services.ClientService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // create
    @PostMapping("/")
    public ResponseEntity<ClientDto> createClient(@Valid @RequestBody ClientDto clientDto) {
        // print the clientDto
        //System.out.println("-------------------->>>>>>"+clientDto);
        ClientDto createdClientDto = clientService.createClient(clientDto);
        return new ResponseEntity<ClientDto>(createdClientDto, HttpStatus.CREATED);
    }

}
