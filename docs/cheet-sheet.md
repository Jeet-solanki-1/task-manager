# 🎯 **COMPLETE SPRING BOOT CHEAT SHEET - Task Manager Edition**

---

## 📦 **1. ESSENTIAL IMPORTS (What they provide)**

```java
// JPA / Database Annotations - Maps Java class to database table
import jakarta.persistence.Entity;           // Marks class as database entity
import jakarta.persistence.Table;            // Specifies table name (optional)
import jakarta.persistence.Id;               // Marks primary key field
import jakarta.persistence.GeneratedValue;   // Auto-generate ID values
import jakarta.persistence.GenerationType;   // ID generation strategy (IDENTITY, AUTO, SEQUENCE)
import jakarta.persistence.Column;           // Maps field to column (nullable, length, unique)
import jakarta.persistence.Enumerated;       // Maps enum to database column
import jakarta.persistence.EnumType;         // ORDINAL (0,1) or STRING ("PENDING")
import jakarta.persistence.PrePersist;       // Runs before insert
import jakarta.persistence.PreUpdate;        // Runs before update

// Validation - Validates data before saving
import jakarta.validation.constraints.NotBlank;      // field cannot be null/empty
import jakarta.validation.constraints.Pattern;       // must match regex
import jakarta.validation.constraints.FutureOrPresent; // date not in past
import jakarta.validation.Valid;                     // triggers validation in controller

// Spring Web - REST API annotations
import org.springframework.web.bind.annotation.RestController;  // Marks REST controller
import org.springframework.web.bind.annotation.RequestMapping;  // Base path for controller
import org.springframework.web.bind.annotation.GetMapping;       // HTTP GET endpoint
import org.springframework.web.bind.annotation.PostMapping;      // HTTP POST endpoint
import org.springframework.web.bind.annotation.PutMapping;       // HTTP PUT endpoint
import org.springframework.web.bind.annotation.PatchMapping;     // HTTP PATCH endpoint
import org.springframework.web.bind.annotation.DeleteMapping;    // HTTP DELETE endpoint
import org.springframework.web.bind.annotation.PathVariable;     // URL parameter {id}
import org.springframework.web.bind.annotation.RequestParam;     // Query parameter ?status=PENDING
import org.springframework.web.bind.annotation.RequestBody;      // JSON request body
import org.springframework.web.bind.annotation.CrossOrigin;      // Allow frontend calls
import org.springframework.http.ResponseEntity;                 // Custom HTTP responses
import org.springframework.http.HttpStatus;                      // HTTP codes (200,201,404,500)

// Spring Core - Dependency Injection
import org.springframework.stereotype.Service;       // Marks service layer bean
import org.springframework.stereotype.Repository;   // Marks repository bean
import org.springframework.stereotype.Component;    // Generic Spring bean
import org.springframework.beans.factory.annotation.Autowired;  // Field injection
import org.springframework.beans.factory.annotation.Value;      // Inject properties

// Transaction Management
import org.springframework.transaction.annotation.Transactional; // Ensures atomic operations

// Spring Data JPA - Database operations
import org.springframework.data.jpa.repository.JpaRepository;    // Provides CRUD methods
import org.springframework.data.jpa.repository.Query;           // Custom JPQL queries
import org.springframework.data.repository.query.Param;          // Named parameters in @Query

// Date/Time - For date validation
import java.time.LocalDate;                          // Date without time
import java.time.LocalDateTime;                      // Date with time
import java.time.format.DateTimeFormatter;           // Parse string to date (dd-MM-yyyy)
import java.time.format.DateTimeParseException;      // Invalid format error

// Lombok - Reduces boilerplate
import lombok.Data;           // Generates getters/setters/toString/equals/hashCode
import lombok.NoArgsConstructor;   // Generates empty constructor
import lombok.AllArgsConstructor;  // Generates constructor with all fields
import lombok.Builder;         // Builder pattern for object creation

// Utilities
import java.util.List;         // List interface
import java.util.Optional;     // Handle null safely
import java.util.stream.Collectors; // Convert List<Entity> to List<DTO>
```

---

## 🏗️ **2. LAYERED ARCHITECTURE ANNOTATIONS**

```java
// ----- ENTITY LAYER (Database table mapping) -----
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String title;
    
    @Enumerated(EnumType.STRING)  // ORDINAL = number, STRING = text
    private Status status = Status.PENDING;
}

// ----- REPOSITORY LAYER (Database operations) -----
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Spring Data JPA automatically implements this!
    List<Task> findByStatus(Status status);
}

// ----- SERVICE LAYER (Business logic) -----
@Service
public class TaskService {
    private final TaskRepository repository;
    
    public TaskService(TaskRepository repository) {  // Constructor injection
        this.repository = repository;
    }
    
    @Transactional
    public Task createTask(Task task) {
        return repository.save(task);
    }
}

// ----- CONTROLLER LAYER (REST endpoints) -----
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;
    
    public TaskController(TaskService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<Task> getAll() {
        return service.getAllTasks();
    }
}
```

---

## 🔄 **3. @Transactional - Complete Guide**

### **What it does:**
- Starts a database transaction before method executes
- Commits automatically if method succeeds
- Rolls back if exception occurs

### **Source:** `import org.springframework.transaction.annotation.Transactional;`

### **Example from your code:**
```java
@Service
public class TaskServiceImpl implements TaskService {
    
    @Override
    @Transactional  // ← Ensures save & any changes happen in ONE transaction
    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        
        Task saved = repository.save(task);
        return convertToResponse(saved);
        // If any exception above, database rolls back → no partial save!
    }
    
    @Override
    @Transactional
    public void deleteTask(Long id) {
        // Check if exists + delete in same transaction
        if (!repository.existsById(id)) {
            throw new RuntimeException("Task not found");  // ← Triggers rollback
        }
        repository.deleteById(id);
    }
}
```

### **What happens without @Transactional:**
```java
// ❌ Without @Transactional - each save is separate!
public void transferMoney() {
    account1.setBalance(0);     // SAVED immediately
    account2.setBalance(100);    // SAVED immediately
    // If error here, account1 still changed!
}
```

### **With @Transactional:**
```java
// ✅ With @Transactional - all or nothing
@Transactional
public void transferMoney() {
    account1.setBalance(0);     // Not saved yet
    account2.setBalance(100);    // Not saved yet
    // If no error, BOTH saved at method end
    // If error, NEITHER saved
}
```

---

## 📁 **4. JPA REPOSITORY - All Methods**

### **Basic CRUD Methods (provided by JpaRepository):**
```java
// CREATE / UPDATE
Task saved = repository.save(task);           // Insert or update
List<Task> saved = repository.saveAll(tasks); // Insert multiple

// READ
Optional<Task> task = repository.findById(1L);           // Get by ID
boolean exists = repository.existsById(1L);              // Check if exists
List<Task> all = repository.findAll();                   // Get all
long count = repository.count();                         // Get total count

// DELETE
repository.deleteById(1L);                  // Delete by ID
repository.delete(task);                     // Delete entity
repository.deleteAll();                      // Delete all
```

### **Derived Query Methods (Spring parses method name to generate SQL):**

```java
// Pattern: findBy[FieldName][Condition]
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // WHERE status = ?
    List<Task> findByStatus(Status status);
    
    // WHERE status = ? ORDER BY due_date ASC
    List<Task> findByStatusOrderByDueDateAsc(Status status);
    
    // WHERE title LIKE %?%
    List<Task> findByTitleContaining(String keyword);
    
    // WHERE due_date < ?
    List<Task> findByDueDateBefore(LocalDate date);
    
    // WHERE due_date > ?
    List<Task> findByDueDateAfter(LocalDate date);
    
    // WHERE status = ? AND due_date < ?
    List<Task> findByStatusAndDueDateBefore(Status status, LocalDate date);
    
    // WHERE status = ? OR status = ?
    List<Task> findByStatusOrStatus(Status status1, Status status2);
    
    // COUNT WHERE status = ?
    long countByStatus(Status status);
    
    // DELETE WHERE status = ?
    long deleteByStatus(Status status);
}
```

### **Custom @Query (for complex queries):**
```java
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    @Query("SELECT t FROM Task t WHERE t.status = :status")
    List<Task> getTasksByStatus(@Param("status") Status status);
    
    @Query("SELECT t FROM Task t WHERE t.dueDate <= :date")
    List<Task> findOverdueTasks(@Param("date") LocalDate date);
    
    @Query("UPDATE Task t SET t.status = 'COMPLETED' WHERE t.dueDate < :today")
    @Modifying
    int autoCompleteOverdueTasks(@Param("today") LocalDate today);
}
```

---

## 🗄️ **5. SQL QUERIES (From H2 Console)**

### **Basic CRUD SQL:**
```sql
-- CREATE Table (JPA does this automatically)
CREATE TABLE tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    status VARCHAR(20) DEFAULT 'PENDING',
    due_date VARCHAR(20)
);

-- INSERT
INSERT INTO tasks (title, description, status, due_date) 
VALUES ('Learn Spring', 'Complete project', 'PENDING', '31-12-2025');

-- SELECT (READ)
SELECT * FROM tasks;                              -- All tasks
SELECT * FROM tasks WHERE id = 1;                  -- Single task
SELECT * FROM tasks WHERE status = 'PENDING';      -- Filter by status
SELECT * FROM tasks ORDER BY due_date DESC;        -- Sort by date

-- UPDATE
UPDATE tasks SET status = 'COMPLETED' WHERE id = 1;
UPDATE tasks SET title = 'New Title', due_date = '01-01-2026' WHERE id = 2;

-- DELETE
DELETE FROM tasks WHERE id = 3;
DELETE FROM tasks WHERE status = 'COMPLETED';

-- COUNT
SELECT COUNT(*) FROM tasks;
SELECT status, COUNT(*) FROM tasks GROUP BY status;
```

### **Advanced SQL:**
```sql
-- Date operations (if using DATE type)
SELECT * FROM tasks WHERE due_date < CURRENT_DATE;  -- Overdue tasks
SELECT * FROM tasks WHERE due_date BETWEEN '2025-01-01' AND '2025-12-31';

-- LIKE (pattern matching)
SELECT * FROM tasks WHERE title LIKE '%Spring%';

-- Pagination
SELECT * FROM tasks LIMIT 10 OFFSET 0;  -- First 10 records

-- Joins (if related tables)
SELECT t.title, c.comment_text 
FROM tasks t 
LEFT JOIN comments c ON t.id = c.task_id;
```

---

## ❌ **6. EXCEPTION HANDLING**

### **Problem: Your code returns 500 crash**

### **Solution 1: Manual Try-Catch in Controller**

```java
@RestController
public class TaskController {
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Long id) {
        try {
            TaskResponse task = service.getTaskById(id);
            if (task == null) {
                return ResponseEntity.notFound().build();  // 404
            }
            return ResponseEntity.ok(task);  // 200
        } catch (Exception e) {
            // Prevent crash - return polite error instead of 500 crash
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
        }
    }
}
```

### **Solution 2: Global Exception Handler (Best Practice)**

```java
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@ControllerAdvice  // Intercepts exceptions from ALL controllers
public class GlobalExceptionHandler {
    
    // Handle task not found
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        if (e.getMessage().contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("{\"error\": \"" + e.getMessage() + "\"}");
    }
    
    // Handle past date validation
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("{\"error\": \"" + e.getMessage() + "\"}");
    }
    
    // Catch any other exception (prevents 500 crash)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("{\"error\": \"Internal server error: " + e.getMessage() + "\"}");
    }
}
```

Now your service can throw exceptions, and controller returns nice JSON instead of crash:

```java
@Service
public class TaskServiceImpl {
    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        return convertToResponse(task);
    }
}
```

---

## 📋 **7. DATE VALIDATION - Past Date Check**

```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public boolean isDueDateValid(String dueDateStr) {
    try {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
        LocalDate today = LocalDate.now();
        
        // isAfter() - true if today is AFTER due date (past)
        if (today.isAfter(dueDate)) {
            return false;  // Past date - invalid
        }
        return true;  // Today or future - valid
        
    } catch (DateTimeParseException e) {
        return false;  // Invalid format
    }
}
```

### **Date Methods Summary:**
| Method | Example | Returns true if |
|--------|---------|-----------------|
| `date1.isAfter(date2)` | `today.isAfter(pastDate)` | today > pastDate |
| `date1.isBefore(date2)` | `today.isBefore(futureDate)` | today < futureDate |
| `date1.isEqual(date2)` | `today.isEqual(today)` | same day |

---

## 🎯 **8. DTO PATTERN (Data Transfer Object)**

```java
// Request DTO - What client sends to API
public class TaskRequest {
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    @FutureOrPresent(message = "Due date cannot be past")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private String dueDate;
}

// Response DTO - What API returns to client
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String status;
    private String dueDate;
    
    // Constructor
    public TaskResponse(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.status = task.getStatus().name();
        this.dueDate = task.getDueDate();
    }
}

// Converting Entity → DTO in Service
private TaskResponse convertToResponse(Task task) {
    return new TaskResponse(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getStatus(),
        task.getDueDate()
    );
}
```

---

## 🚀 **9. ONE-LINE SUMMARY**

```
Spring Boot = @Entity (table) + JpaRepository (methods) + @Service (logic) + @RestController (API) + @Transactional (atomic) + GlobalExceptionHandler (no crashes)
```

---

## 📚 **10. QUICK REFERENCE CARD**

```yaml
# LAYERS
Controller → @RestController, @GetMapping, @PostMapping, @PathVariable, @RequestParam
Service    → @Service, @Transactional
Repository → @Repository, extends JpaRepository<Entity, ID>
Entity     → @Entity, @Table, @Id, @GeneratedValue, @Column

# VALIDATION
@NotBlank, @Pattern(regexp="..."), @FutureOrPresent, @Valid

# EXCEPTIONS
Controller try-catch → ResponseEntity.status()
OR
@ControllerAdvice → @ExceptionHandler

# DATE
LocalDate.now(), .isAfter(), .isBefore(), .isEqual()
DateTimeFormatter.ofPattern("dd-MM-yyyy")

# HTTP STATUS
200 OK, 201 CREATED, 204 NO_CONTENT, 400 BAD_REQUEST, 404 NOT_FOUND, 500 ERROR
ResponseEntity.ok(body) → 200
ResponseEntity.status(HttpStatus.CREATED).body(body) → 201
ResponseEntity.noContent().build() → 204
ResponseEntity.notFound().build() → 404
```

**Keep this cheat sheet handy while building your next projects! 🚀**