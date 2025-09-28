package com.example.taskapp.dao;

import com.example.taskapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDAO extends JpaRepository<Task, Long> {
    List<Task> findByProject_ProjectId(Long projectId);
}
