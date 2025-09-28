package com.example.taskapp.service;

import com.example.taskapp.dao.CommentDAO;
import com.example.taskapp.dao.TaskDAO;
import com.example.taskapp.exceptions.CommentNotFoundException;
import com.example.taskapp.exceptions.NotFoundException;
import com.example.taskapp.exceptions.TaskNotFoundException;
import com.example.taskapp.model.Comment;
import com.example.taskapp.model.Task;
import com.example.taskapp.utilities.ModelUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private ModelUpdater modelUpdater;

    public Comment createComment(Comment comment) {
        Task task=taskDAO.findById(comment.getTask().getTaskId()).get();
        if(task==null){
            throw new NotFoundException("task not found");
        }
        comment.setTask(task);
        return commentDAO.save(comment);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        Comment existingComment = commentDAO.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
        Comment existing = modelUpdater.updateFields(existingComment, commentDetails);
        return commentDAO.save(existing);
    }

    public Comment getCommentById(Long id) {
        return commentDAO.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
    }
    public void deleteCommentById(Long id) {
        Comment comment = commentDAO.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found with id: " + id));
        commentDAO.delete(comment);
    }
    public List<Comment> getAllCommentsByTask(Task task) {
        return commentDAO.findByTask(task);
    }
    public List<Comment> getAllCommentsByTaskId(Long taskId) {
        Task task = taskDAO.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));;
        return commentDAO.findByTask(task);
    }
}

