package com.jlss.task_manager.service;

import org.springframework.stereotype.Service;
import com.jlss.task_manager.entity.Task;
import com.jlss.task_manager.repository.TaskRepository;
import com.jlss.task_manager.model.TaskResponse; 
@Service
public class TaskServiceImpl implements TaskService   {
 	private final TaskRepository repository;
 	public TaskServiceImpl(TaskRepository repository){
 		this.repository=repository;
 	}
 	@Override
 	public TaskResponse getTaskById(Long id){
 		//todo use global exception handling! made one exception custom class at a time and use it in one api and  test and move

 		Task task = repository.findById(id).orElse(null);
 		if (task==null){
 			return null;
 		}
 		return new TaskResponse(
 		  	task.getId(),
		    task.getTitle(),
		    task.getDescription(),
		    task.getStatus(),
		    task.getDueDate()
		);
 	}
 }