package com.blogapp.apis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.blogapp.apis.payload.APIResponse;
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
       
        ClientDto createdClientDto = clientService.createClient(clientDto);
        return new ResponseEntity<ClientDto>(createdClientDto, HttpStatus.CREATED);
    }

    // update
    @PutMapping("/{clientId}")
    public ResponseEntity<ClientDto> updateClient(@Valid @RequestBody ClientDto clientDto, @PathVariable Integer clientId) {
        ClientDto updatedClientDto = clientService.updateClient(clientDto, clientId);
        return new ResponseEntity<ClientDto>(updatedClientDto, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/{clientId}")
    public ResponseEntity<APIResponse> deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<APIResponse>(new APIResponse("Category deleted successfully", true, HttpStatus.OK),
				HttpStatus.OK);
    }

    // get
    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Integer clientId) {
        ClientDto clientDto = clientService.getClient(clientId);
        return new ResponseEntity<ClientDto>(clientDto, HttpStatus.OK);
    }

    // get all
    @GetMapping("/")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clientDtos = clientService.getAllClients();
        return new ResponseEntity<List<ClientDto>>(clientDtos, HttpStatus.OK);
    }

}
