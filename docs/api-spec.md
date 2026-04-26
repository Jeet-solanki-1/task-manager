## 🎯 **Converted API Calls to Match Your Regex Format (`DD-MM-YYYY`)**

Your regex expects: `"dueDate": "31-12-2025"` (DD-MM-YYYY), NOT ISO format with time.

---

## ✅ **Updated API Calls (Compatible with Your Regex)**

### **API 1: Create a Task (POST)**
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Learn Spring Boot",
    "description": "Complete Task Manager project",
    "dueDate": "31-12-2025"
  }'
```

**Expected Response:**
```json
{
  "id": 1,
  "title": "Learn Spring Boot",
  "description": "Complete Task Manager project",
  "dueDate": "31-12-2025",
  "status": "PENDING"
}
```

---

### **API 2: Create Another Task**
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Write Documentation",
    "description": "Document all APIs",
    "dueDate": "15-11-2025"
  }'
```

---

### **API 3: Get All Tasks (GET)**
```bash
curl -X GET http://localhost:8080/api/tasks
```

---

### **API 4: Get Pending Tasks Only**
```bash
curl -X GET "http://localhost:8080/api/tasks/status?status=PENDING"
```

---

### **API 5: Get Completed Tasks Only**
```bash
curl -X GET "http://localhost:8080/api/tasks/status?status=COMPLETED"
```

---

### **API 6: Update Task (PUT)**
```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Learn Spring Boot Advanced",
    "description": "Complete Task Manager + Add caching",
    "dueDate": "15-01-2026"
  }'
```

---

### **API 7: Mark Task as Complete (PATCH)**
```bash
curl -X PATCH http://localhost:8080/api/tasks/1/COMPLETED
```

---

### **API 8: Mark Task as Pending (PATCH)**
```bash
curl -X PATCH http://localhost:8080/api/tasks/1/PENDING
```

---

### **API 9: Delete Task (DELETE)**
```bash
curl -X DELETE http://localhost:8080/api/tasks/2
```

---

### **API 10: Get Single Task**
```bash
curl -X GET http://localhost:8080/api/tasks/1
```

---

## 🧪 **Complete Test Scenario (All Compatible with Your Regex)**

```bash
# 1. Create task
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title":"Task 1","description":"First task","dueDate":"31-12-2025"}'

# 2. Create second task
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title":"Task 2","description":"Second task","dueDate":"31-12-2025"}'

# 3. Get all tasks (should show both)
curl -X GET http://localhost:8080/api/tasks

# 4. Mark task 1 as complete
curl -X PATCH http://localhost:8080/api/tasks/1/COMPLETED

# 5. Get pending tasks (should show only task 2)
curl -X GET "http://localhost:8080/api/tasks/status?status=PENDING"

# 6. Get completed tasks (should show task 1)
curl -X GET "http://localhost:8080/api/tasks/status?status=COMPLETED"

# 7. Update task 2
curl -X PUT http://localhost:8080/api/tasks/2 -H "Content-Type: application/json" -d '{"title":"Updated Task 2","description":"Updated description","dueDate":"15-01-2026"}'

# 8. Delete task 2
curl -X DELETE http://localhost:8080/api/tasks/2

# 9. Get all tasks (should show only task 1)
curl -X GET http://localhost:8080/api/tasks
```

---

## 📋 **Summary of Changes Made**

| Original Format | New Format (Your Regex) |
|----------------|-------------------------|
| `"dueDate": "2025-12-31T23:59:59"` | `"dueDate": "31-12-2025"` |
| `?status=pending` | `?status=PENDING` |
| `?status=completed` | `?status=COMPLETED` |
| `/tasks/1/complete` | `/tasks/1/COMPLETED` |
| `/tasks/1/pending` | `/tasks/1/PENDING` |

---

## ✅ **Now Your APIs Will Work With Your Regex!**

Run these commands in **Git Bash** and your Task Manager API will accept all requests without validation errors! 🚀

## Pagination api spec just copy and test!
```
# Create task 1
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title":"Spring Boot Learning","description":"Learn pagination","dueDate":"31-12-2026"}'

# Create task 2
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title":"Spring Data JPA","description":"Master repositories","dueDate":"31-12-2026"}'

# Create task 3
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title":"Task Manager Project","description":"Complete all features","dueDate":"31-12-2026"}'

# Mark task 1 as completed
curl -X PATCH http://localhost:8080/api/tasks/1/COMPLETED

# Case 1: No filters
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc"

# Case 2: Filter by PENDING status
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc&status=PENDING"

# Case 3: Filter by COMPLETED status
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc&status=COMPLETED"

# Case 4: Search by title
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc&search=Spring"

# Case 5: Status + Search together
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc&status=PENDING&search=Spring"

# Case 6: Pagination - second page
curl "http://localhost:8080/api/tasks?page=1&size=2&sortBy=id&direction=asc"

# Case 7: Sort by title ascending
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=title&direction=asc"

# Case 8: Sort by title descending
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=title&direction=desc"

# Case 9: All combined
curl "http://localhost:8080/api/tasks?page=0&size=5&sortBy=title&direction=asc&status=PENDING&search=Spring"

# Case 10: No results
curl "http://localhost:8080/api/tasks?page=0&size=10&search=NonExistentKeyword"
```
