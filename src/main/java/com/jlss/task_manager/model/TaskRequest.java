package com.jlss.task_manager.model;
import lombok.Data;

@Data
public class TaskRequest{
	private String title;
	private String description;
	private String dueDate;
}