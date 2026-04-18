package com.jlss.task_manager.service;

import com.jlss.task_manager.model.TaskResponse;

public interface TaskService{
	public TaskResponse getTaskById(Long id);
}