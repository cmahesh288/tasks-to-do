package com.bdiplus.entity;

import jakarta.persistence.*; 

@Entity
public class Task { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 
    private String task; 
  
    public Task(String task) { 
        this.task = task; 
    } 
    
    public Task() { 
     
    } 
    
    public Long getId() { 
        return id; 
    } 
    public void setId(Long id) { 
        this.id = id; 
    } 
    public String getTask() { 
        return task; 
    } 
    public void setTask(String task) { 
        this.task = task; 
    } 
} 