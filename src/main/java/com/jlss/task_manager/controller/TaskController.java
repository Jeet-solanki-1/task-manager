package com.jlss.task_manager.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.jlss.task_manager.model.*;
import com.jlss.task_manager.service.TaskService;
import com.jlss.task_manager.enums.Status;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

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
	@PostMapping()
	public TaskResponse createTask(@Valid @RequestBody TaskRequest request){
		
		return service.createTask(request);
	}
	@GetMapping
	public ResponseEntity<Page<TaskResponse>> getTasks(
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "10") int size,
	    @RequestParam(defaultValue = "id") String sortBy,
	    @RequestParam(defaultValue = "asc") String direction,
	    @RequestParam(required = false) Status status,
	    @RequestParam(required = false) String search) {

	Page<TaskResponse> taskPage = service.getTasks(status, search, page, size, sortBy, direction);
	return ResponseEntity.ok(taskPage);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @RequestBody TaskRequest request){
		TaskResponse updated = service.updateTask(id, request);
		return ResponseEntity.ok(updated);
	}   
	@PatchMapping("/{id}/{status}")
	public TaskResponse patchTask(@PathVariable Long id, @PathVariable Status status){
		return service.patchTask(id,status);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id){
		service.deleteTask(id);
		return ResponseEntity.noContent().build(); 
	}


}