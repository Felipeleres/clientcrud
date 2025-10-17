package com.felipeleres.clientcrud.services;

import com.felipeleres.clientcrud.dto.ClientDTO;
import com.felipeleres.clientcrud.entities.Client;
import com.felipeleres.clientcrud.repositories.ClientRepository;

import com.felipeleres.clientcrud.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado!"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO){
        Client client = new Client();
        dtoToEntity(clientDTO, client);
        client =  clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update (Long id,ClientDTO clientDTO){
        try {
            Client client = clientRepository.getReferenceById(id);
            dtoToEntity(clientDTO, client);
            client = clientRepository.save(client);
            return new ClientDTO(client);
        } catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
    }

    public void delete (Long id){

        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado!");
        }
            clientRepository.deleteById(id);
    }



    public Client dtoToEntity(ClientDTO clientDTO,Client client){
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
        return client;
    }

















}
