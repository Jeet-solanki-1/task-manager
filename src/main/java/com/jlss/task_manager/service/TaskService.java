package com.jlss.task_manager.service;

import com.jlss.task_manager.model.*;
import com.jlss.task_manager.enums.Status;
import java.util.List;
import  org.springframework.data.domain.Page;

public interface TaskService{
	public TaskResponse getTaskById(Long id);
	public TaskResponse createTask(TaskRequest request);
	public Page<TaskResponse> getTasks(Status status, String search, int page, int size, String sortBy, String direction);
	public TaskResponse updateTask(Long id, TaskRequest request);
	public TaskResponse patchTask(Long id, Status status);
	public void deleteTask(Long id);
}