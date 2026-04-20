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