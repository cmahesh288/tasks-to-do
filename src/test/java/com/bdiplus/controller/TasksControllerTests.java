package com.bdiplus.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.bdiplus.entity.Task;
import com.bdiplus.service.TasksService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class TasksControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TasksService tasksService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenTask_whenCreateTask_thenReturnStatusOk() throws Exception{
        Task task = new Task("complete something");
        given(tasksService.createNewTask(any(Task.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/tasks/addTask"));

        response.andDo(print()).
                andExpect(status().isOk());

    }

    @Test
    public void givenListOfTasks_whenGetAllTasks_thenReturnTasksList() throws Exception{
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("complete abc"));
        tasks.add(new Task("complete def"));
        given(tasksService.getAllTask()).willReturn(tasks);

        ResultActions response = mockMvc.perform(get("/tasks/getAllTasks"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(tasks.size())));

    }

    // positive scenario
    @Test
    public void givenTaskId_whenGetTaskById_thenReturnTask() throws Exception{
        long taskId = 1L;
        Task task = new Task("comeplete abc");
        given(tasksService.findTaskById(taskId)).willReturn(task);

        ResultActions response = mockMvc.perform(get("/tasks/getTask/{id}", taskId));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(task.getId())))
                .andExpect(jsonPath("$.task", is(task.getTask())));

    }

    // negative scenario 
    @Test
    public void givenInvalidTaskId_whenGetTaskId_thenReturnNull() throws Exception{
        long taskId = 0L;
        Task task = new Task("comeplete abc");
        given(tasksService.findTaskById(taskId)).willReturn(task);

        ResultActions response = mockMvc.perform(get("/tasks/getTask/{id}", taskId));
        given(tasksService.findTaskById(taskId)).willReturn(null);

        response.andExpect(status().isOk())
                .andDo(print());

    }

    @Test
        public void givenUpdatedTask_whenUpdateTask_thenReturnUpdatedTask() throws Exception{
        	 long taskId = 1L;
             Task task = new Task("comeplete abc");
             
             Task updatedTask = new Task("comeplete abc soon");

            given(tasksService.findTaskById(taskId)).willReturn(task);
            given(tasksService.updateTask(any(Task.class)))
                    .willAnswer((invocation)-> invocation.getArgument(0));

            ResultActions response = mockMvc.perform(put("/tasks/updateTask/{id}", taskId)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(updatedTask)));


            response.andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(jsonPath("$.task", is(updatedTask.getTask())));
        }

    @Test
    public void givenTaskId_whenDeleteTask_thenReturn200() throws Exception{
        long taskId = 1L;
        willDoNothing().given(tasksService).deleteTask(taskId);

        ResultActions response = mockMvc.perform(delete("/tasks/deleteTask/{id}", taskId));

        response.andExpect(status().isOk())
                .andDo(print());
    }
}