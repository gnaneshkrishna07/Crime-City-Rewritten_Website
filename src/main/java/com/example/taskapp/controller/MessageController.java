package com.example.taskapp.controller;

import com.example.taskapp.exceptions.MessageNotFoundException;
import com.example.taskapp.model.Employee;
import com.example.taskapp.model.Message;
import com.example.taskapp.service.MessageService;
import com.example.taskapp.service.EmployeeService;
import com.example.taskapp.utilities.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<BaseResponse<Message>> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        BaseResponse<Message> response = new BaseResponse<>("Message created successfully", createdMessage, HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/sender/{senderId}")
    public ResponseEntity<BaseResponse<List<Message>>> getMessagesBySender(@PathVariable Long senderId) {
        List<Message> messages = messageService.getMessagesBySender(senderId);
        if (messages.isEmpty()) {
            throw new MessageNotFoundException("No messages found for sender ID: " + senderId);
        }
        BaseResponse<List<Message>> response = new BaseResponse<>("Messages retrieved successfully", messages, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<BaseResponse<List<Message>>> getMessagesByReceiver(@PathVariable Long receiverId) {
        List<Message> messages = messageService.getMessagesByReceiver(receiverId);
        if (messages.isEmpty()) {
            throw new MessageNotFoundException("No messages found for receiver ID: " + receiverId);
        }
        BaseResponse<List<Message>> response = new BaseResponse<>("Messages retrieved successfully", messages, HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
