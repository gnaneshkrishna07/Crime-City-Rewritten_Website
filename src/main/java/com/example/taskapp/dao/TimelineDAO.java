package com.example.taskapp.dao;

import com.example.taskapp.model.Timeline;
import com.example.taskapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelineDAO extends JpaRepository<Timeline, Long> {
    List<Timeline> findByTask(Task task);
}
