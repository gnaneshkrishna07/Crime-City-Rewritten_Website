package com.example.taskapp.dao;

import com.example.taskapp.model.Employee;
import com.example.taskapp.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageDAO extends JpaRepository<Message, Long> {
    List<Message> findBySender(Employee sender);
    List<Message> findByReceiver(Employee receiver);
}
