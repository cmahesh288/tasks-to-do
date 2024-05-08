package com.bdiplus.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.bdiplus.entity.Task;

import java.util.List; 
  
@Repository
public interface TasksRepository extends JpaRepository<Task, Long> { 
    public Task findByTask(String task);
    public List<Task> findAll(); 
    public Task getById(Long id); 
} 