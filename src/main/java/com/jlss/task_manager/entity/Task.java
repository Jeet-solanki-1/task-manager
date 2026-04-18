// a entity
package com.jlss.task_manager.entity;
// do i need to import Status? but i am in the same module! still?
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import com.jlss.task_manager.enums.Status;

@Entity
@Table(name="tasks")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;


	@Enumerated(EnumType.ORDINAL) // SURE? yes status should not be null only to possibilities
	private Status status = Status.PENDING; // initially tasks are pending

	@Column(nullable=false)
	private String title; // in future i will like to make title as task 1 , 2 etc as default and for that i will fetch db when returing or saving first!
	@Column(length=500)
	private String description;

	@Column(name="due_date", nullable=false)
	@Pattern(regexp="^(0[0-9]|[12][0-9]|3[01])-(0[0-9]|1[0-2])-[0-9]{4}$") 
	private String dueDate;

	 // does it could be null? // NOTE: it fails in leap year like 31st feb don't exists! but current setup allows it.
	// i will fix leap year problems later!
	// and also it should be LocalDateTime? or if not
	// then in backedn how we are gonna check that the due date is over?
	// i think we can use regex here:
	// means dueDate should be like "^[0-9]{2}-[0-9]{2}-[0-9]{4}$" ->
								//   mm-dd-year
	// and now we have a constand formate so we now able to provide lists of taks
	// whose dueDate is closer and whose due data is farer! and even we can 
	// only list on searching due date !

}