package com.example.taskapp.service;

import com.example.taskapp.dao.EmployeeDAO;
import com.example.taskapp.dao.MessageDAO;
import com.example.taskapp.exceptions.MessageNotFoundException;
import com.example.taskapp.model.Employee;
import com.example.taskapp.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    // Method to create a new message
    public Message createMessage(Message message) {
        return messageDAO.save(message);
    }

    // Method to get all messages from a specific sender
    public List<Message> getMessagesBySender(Long senderId) {
        Employee sender = employeeDAO.findById(senderId)
                .orElseThrow(() -> new MessageNotFoundException("Sender not found for ID: " + senderId));
        return messageDAO.findBySender(sender);
    }

    // Method to get all messages for a specific receiver
    public List<Message> getMessagesByReceiver(Long receiverId) {
        Employee receiver = employeeDAO.findById(receiverId)
                .orElseThrow(() -> new MessageNotFoundException("Receiver not found for ID: " + receiverId));
        return messageDAO.findByReceiver(receiver);
    }
}
