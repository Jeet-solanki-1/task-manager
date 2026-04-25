package com.jlss.task_manager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jlss.task_manager.entity.Task;
import com.jlss.task_manager.repository.TaskRepository;
import com.jlss.task_manager.model.*; 
import java.util.stream.Collectors;
import java.util.List;
import com.jlss.task_manager.enums.Status;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
 		return convertToResponse(task);
 	}
 	@Override
 	@Transactional
 	public TaskResponse createTask(TaskRequest request){
 		if (!isDueDateValid(request.getDueDate())){
 			throw new RuntimeException("due date cant be in past!!");
 		}
 		Task task = new Task();
		task.setTitle(request.getTitle());
		task.setDescription(request.getDescription());
		task.setDueDate(request.getDueDate());

		
        Task saved = repository.save(task);

 		return convertToResponse(saved);
 	}
 	public boolean isDueDateValid(String dueDateStr){
 		try{
 			DateTimeFormatter df =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
 			LocalDate dueDate = LocalDate.parse(dueDateStr,df);
 			LocalDate today = LocalDate.now();
 			if (today.isAfter(dueDate)){
 				return false;
 			}
 			return true;
 		}catch(DateTimeParseException e){
 			return false;
 		}
 	}
 	@Override
	public List<TaskResponse> getAllTasks(){
		return repository.findAll()
		 		.stream().map(task -> convertToResponse(task)).collect(Collectors.toList());
	}
	@Override
	public List<TaskResponse> getTasksByStatus(Status status){
		return repository.findByStatus(status)
				.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public TaskResponse updateTask(Long id, TaskRequest request){
		Task old = repository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
		old.setTitle(request.getTitle());
		old.setDescription(request.getDescription());
		old.setDueDate(request.getDueDate());
		Task updated = repository.save(old);
		return convertToResponse(updated);
	}
	@Override
	@Transactional
	public TaskResponse patchTask(Long id, Status status){
    	Task task = repository.findById(id)
        	.orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        task.setStatus(status);
        Task updated = repository.save(task);
		return convertToResponse(updated);
	}
	@Override
	@Transactional
	public void deleteTask(Long id){
		if (!repository.existsById(id)) {
        	throw new RuntimeException("Task not found with id: " + id);
    	}
    	repository.deleteById(id);
	}

	//helper method to convert Task into TaskResponse
	private TaskResponse convertToResponse(Task task){
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