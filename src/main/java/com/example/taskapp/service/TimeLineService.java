package com.example.taskapp.service;

import com.example.taskapp.dao.EmployeeDAO;
import com.example.taskapp.dao.TaskDAO;
import com.example.taskapp.dao.TimelineDAO;
import com.example.taskapp.exceptions.NotFoundException;
import com.example.taskapp.exceptions.TaskNotFoundException;
import com.example.taskapp.model.Employee;
import com.example.taskapp.model.Task;
import com.example.taskapp.model.Timeline;
import com.example.taskapp.exceptions.TimelineNotFoundException;
import com.example.taskapp.utilities.ModelUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeLineService {

    @Autowired
    private TimelineDAO timelineRepository;

    @Autowired
    private ModelUpdater modelUpdater;

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private EmployeeDAO employeeDAO;


    // Create a new timeline
    public Timeline createTimeline(Timeline timeline) {
        Task task=taskDAO.findById(timeline.getTask().getTaskId()).get();
        if(task==null){
            throw new NotFoundException("task not found");
        }
        timeline.setTask(task);
        return timelineRepository.save(timeline);
    }

    // Update an existing timeline using ModelUpdater
    public Timeline updateTimeline(Long id, Timeline updatedTimeline) throws TimelineNotFoundException {
        Timeline existingTimeline = timelineRepository.findById(id)
                .orElseThrow(() -> new TimelineNotFoundException("Timeline not found with id: " + id));

        // Use ModelUpdater to update fields
        modelUpdater.updateFields(existingTimeline, updatedTimeline);

        return timelineRepository.save(existingTimeline);
    }

    // Delete a timeline by ID
    public void deleteTimeline(Long id) throws TimelineNotFoundException {
        Timeline timeline = timelineRepository.findById(id)
                .orElseThrow(() -> new TimelineNotFoundException("Timeline not found with id: " + id));
        timelineRepository.delete(timeline);
    }

    // Get a timeline by ID
    public Timeline getTimelineById(Long id) throws TimelineNotFoundException {
        return timelineRepository.findById(id)
                .orElseThrow(() -> new TimelineNotFoundException("Timeline not found with id: " + id));
    }

    // Get all timelines
    public List<Timeline> fetchAllTimelines() {
        return timelineRepository.findAll();
    }

    public List<Timeline> getAllTimelinesByTaskId(Long taskId) {
        Task task = taskDAO.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + taskId));
        return timelineRepository.findByTask(task);
    }
}