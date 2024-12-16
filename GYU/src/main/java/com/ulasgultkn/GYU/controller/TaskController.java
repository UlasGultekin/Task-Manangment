package com.ulasgultkn.GYU.controller;


import com.ulasgultkn.GYU.entity.Task;
import com.ulasgultkn.GYU.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
       return taskService.deleteTask(id);
    }

    @PutMapping("{taskId}/assign/{userId}")
    public ResponseEntity<?> assignTaskToUser(@PathVariable Long taskId,@PathVariable Long userId){
        return taskService.assignTaskToUser(taskId,userId);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTaskByUser(@PathVariable Long userId){
        return taskService.getTaskByUser(userId);
    }

}