package com.blogapp.apis.services.impl;
import java.util.stream.Collectors;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.apis.entities.Category;
import com.blogapp.apis.entities.Client;
import com.blogapp.apis.exceptions.ResourceNotFindException;
import com.blogapp.apis.payload.ClientDto;
import com.blogapp.apis.repositories.ClientRepository;
import com.blogapp.apis.services.ClientService;


@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    
    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        client = clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto, Integer clientId) {
         Client client = modelMapper.map(clientDto, Client.class);
        client = clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    public void deleteClient(Integer clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> {
			return new ResourceNotFindException("client", "clientId", clientId);
		});

		clientRepository.delete(client);
    }

    @Override
    public ClientDto getClient(Integer clientId) {
         Client client = modelMapper.map(clientId, Client.class);
        client = clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);
    }

    @Override
    public List<ClientDto> getAllClients() {
        

        return clientRepository.findAll().stream().map(client -> modelMapper.map(client, ClientDto.class)).collect(Collectors.toList());
    }
}
