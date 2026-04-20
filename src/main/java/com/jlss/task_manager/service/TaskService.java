package com.jlss.task_manager.service;

import com.jlss.task_manager.model.*;
import com.jlss.task_manager.enums.Status;
import java.util.List;
public interface TaskService{
	public TaskResponse getTaskById(Long id);
	public TaskResponse createTask(TaskRequest request);
	public List<TaskResponse> getAllTasks();
	public List<TaskResponse> getTasksByStatus(Status status);
	public TaskResponse updateTask(Long id, TaskRequest request);
	public TaskResponse patchTask(Long id, Status status);
	public void deleteTask(Long id);
}