package com.bdiplus.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*;

import com.bdiplus.entity.Task;
import com.bdiplus.service.TasksService;

import java.util.List; 
  
@RestController
@RequestMapping("tasks") 
public class TasksController { 
  
    @Autowired
    private TasksService taskService; 
    
    @GetMapping("/getAllTasks") 
    public List<Task> getAllTasks() { 
        return taskService.getAllTask(); 
    } 
   
    @GetMapping("/getTask/{id}") 
    public ResponseEntity<Task> getTask(@PathVariable Long id) { 
        return ResponseEntity.ok(taskService.findTaskById(id)); 
    } 
    
    @PostMapping("/addTask") 
    public boolean createTask(@RequestBody Task task) { 
        return taskService.createNewTask(task); 
    } 
    
    @PutMapping("/updateTask/{id}") 
    public boolean updateTask(@PathVariable Long id, @RequestBody Task task) { 
        task.setId(id); 
        return taskService.updateTask(task); 
    } 
    
    @DeleteMapping("/deleteTask/{id}") 
    public boolean getAllTasks(@PathVariable Long id) { 
        return taskService.deleteTask(id); 
        
    } 
}
