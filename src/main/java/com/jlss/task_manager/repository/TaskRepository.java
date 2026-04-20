package com.jlss.task_manager.repository;

import com.jlss.task_manager.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.jlss.task_manager.enums.Status;
@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{

    @Query("SELECT t FROM Task t WHERE t.status = :status")
	List<Task> findByStatus(@Param("status") Status status);
}
