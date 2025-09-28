package com.example.taskapp.service;

import com.example.taskapp.dao.ClientDAO;
import com.example.taskapp.exceptions.ClientNotFoundException;
import com.example.taskapp.exceptions.NotFoundException;
import com.example.taskapp.exceptions.ProjectNotFoundException;
import com.example.taskapp.model.Client;
import com.example.taskapp.utilities.ModelUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private ModelUpdater modelUpdater;

    public Client createClient(Client client){
        return clientDAO.save(client);
    }

    public Client updateClient(Long id, Client clientDetails) {
        Optional<Client> existingClientOptional = clientDAO.findById(id);
        if (existingClientOptional.isPresent()) {
            Client existingClient = existingClientOptional.get();

            modelUpdater.updateFields(existingClient, clientDetails);

            return clientDAO.save(existingClient);
        }
        else {
            throw new RuntimeException("Client not found with id " + id);
        }
    }

    public Client getClientById(long id){
        return clientDAO.findById(id).orElseThrow(()->
                new ClientNotFoundException("Client not found with id " + id)
        );
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public Client deleteClientById(Long id) {
        if (clientDAO.existsById(id)) {
            Client client = clientDAO.findById(id).get();
            clientDAO.deleteById(id);
            return client;
        } else {
            throw new ClientNotFoundException("Client not found with id " + id);
        }
    }

    public  Client getClientByName(String name){
        Client dbClient=clientDAO.findByCompanyName(name);
        if(dbClient==null){
            throw new NotFoundException("Client with name: "+name+" not found");
        }
        return dbClient;
    }
}

