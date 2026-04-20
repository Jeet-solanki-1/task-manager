package com.jlss.task_manager.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.jlss.task_manager.model.*;
import com.jlss.task_manager.service.TaskService;
import com.jlss.task_manager.enums.Status;
import java.util.List;
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
	public TaskResponse createTask(@RequestBody TaskRequest request){
		return service.createTask(request);
	}
	@GetMapping()
	public  List<TaskResponse> getAllTasks(){
		return service.getAllTasks();
	}
	@GetMapping("/status")
	public List<TaskResponse> getTasksByStatus(@RequestParam Status status){
		return service.getTasksByStatus(status);
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