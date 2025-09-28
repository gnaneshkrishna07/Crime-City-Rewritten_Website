package com.example.taskapp.dao;

import com.example.taskapp.model.Comment;
import com.example.taskapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDAO extends JpaRepository<Comment,Long> {
    List<Comment> findByTask(Task task);
}
