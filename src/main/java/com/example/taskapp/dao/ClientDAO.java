package com.example.taskapp.dao;

import com.example.taskapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDAO extends JpaRepository<Client,Long> {
    Client findByCompanyName(String name);
}