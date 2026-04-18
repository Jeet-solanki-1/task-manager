package com.jlss.task_manager.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.jlss.task_manager.model.*;
import com.jlss.task_manager.service.TaskService;

// why need valid here? @Valid?

@RestController
@RequestMapping("/tasks")
public class TaskController{
	private final TaskService service;
	public TaskController(TaskService service){
		this.service=service;
	}
	@GetMapping("/{id}")
	public TaskResponse getTaskById(@PathVariable Long id){
		return service.getTaskById(id);
	}
}