package com.jlss.task_manager.model;

import lombok.*;
import com.jlss.task_manager.enums.Status;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse{
	private Long id;
    private String title;
    private String description;
    private Status status;
    private String dueDate;

}