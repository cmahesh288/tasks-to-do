package com.bdiplus.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.bdiplus.entity.Task;
import com.bdiplus.repository.TasksRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 
  
@Service
public class TasksService { 
    @Autowired
    private TasksRepository taskRepository; 
      
    public boolean createNewTask(Task task) { 
    	boolean isAdded = true;
        try {
        	taskRepository.save(task); 
        } catch (Exception e) {
        	isAdded = false;
        	System.out.println(e.getMessage());
        }
        return isAdded;
    } 
      
    public List<Task> getAllTask() { 
    	List<Task> tasks = new ArrayList<>();
    	try {
    		tasks = taskRepository.findAll();
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
        return tasks;
    } 
      
    public Task findTaskById(Long id) { 
    	Task task = null;
    	try {
            task = taskRepository.getById(id); 
            } catch (Exception e) {
        		System.out.println(e.getMessage());
            }
    	return task;
    } 
      
    public boolean deleteTask(Long id) { 
    	boolean isDeleted = true;
    	try {
            taskRepository.deleteById(id); 
    	} catch (Exception e) {
    		isDeleted = false;
    		System.out.println(e.getMessage());
    	}
    	return isDeleted;
    } 
      
    public boolean updateTask(Task task) { 
    	boolean isUpdated = true;
        try {
        	taskRepository.save(task); 
        } catch (Exception e) {
        	isUpdated = false;
        	System.out.println(e.getMessage());
        }
        return isUpdated;
    } 
} 