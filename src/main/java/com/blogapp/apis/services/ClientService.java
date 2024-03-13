package com.blogapp.apis.services;

import java.util.List;

import com.blogapp.apis.payload.ClientDto;
public interface ClientService {

    //create
    public ClientDto createClient(ClientDto clientDto);

    //update
    public ClientDto updateClient(ClientDto clientDto, Integer clientId);

    //delete
    public void deleteClient(Integer clientId);

    //get
    public ClientDto getClient(Integer clientId);

    //getAll
    public List<ClientDto> getAllClients();


}
