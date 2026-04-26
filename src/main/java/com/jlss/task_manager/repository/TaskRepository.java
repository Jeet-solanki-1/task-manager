package com.jlss.task_manager.repository;

import com.jlss.task_manager.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.jlss.task_manager.enums.Status;
import  org.springframework.data.domain.Page;
import  org.springframework.data.domain.Pageable;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    Page<Task> findAll(Pageable pageable);
 	  Page<Task> findByStatus(Status status, Pageable pageable);
    Page<Task> findByTitleContaining(String search, Pageable pageable);  
    Page<Task> findByStatusAndTitleContaining(Status status, String search, Pageable pageable);
}


/**
 * -- Search for tasks with 'Spring' in title
SELECT * FROM tasks 
WHERE title LIKE '%Spring%' 
ORDER BY id ASC 
LIMIT 10 OFFSET 0;

-- Count query for pagination
SELECT COUNT(*) FROM tasks 
WHERE title LIKE '%Spring%';

-- Get pending tasks, page 0, size 10, sorted by id ASC
SELECT * FROM tasks 
WHERE status = 'PENDING' 
ORDER BY id ASC 
LIMIT 10 OFFSET 0;

-- Count total pending tasks
SELECT COUNT(*) FROM tasks 
WHERE status = 'PENDING';

-- Pending tasks with 'Spring' in title
SELECT * FROM tasks 
WHERE status = 'PENDING' 
  AND title LIKE '%Spring%' 
ORDER BY id ASC 
LIMIT 10 OFFSET 0;

-- Count query
SELECT COUNT(*) FROM tasks 
WHERE status = 'PENDING' 
  AND title LIKE '%Spring%';

  -- Get all tasks, page 0, size 10, sorted by due_date DESC
SELECT * FROM tasks 
ORDER BY due_date DESC 
LIMIT 10 OFFSET 0;

-- Count total tasks
SELECT COUNT(*) FROM tasks;
 * */
