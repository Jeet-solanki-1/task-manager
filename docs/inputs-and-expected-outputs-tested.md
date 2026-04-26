## 📋 **Formatted API Output for Your README**

Here's the cleaned-up, readable output for each test case:

---

### **Create Task 1**
```bash
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title":"Spring Boot Learning","description":"Learn pagination","dueDate":"31-12-2026"}'
```
**Response:**
```json
{"id":1,"title":"Spring Boot Learning","description":"Learn pagination","status":"PENDING","dueDate":"31-12-2026"}
```

---

### **Create Task 2**
```bash
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title":"Spring Data JPA","description":"Master repositories","dueDate":"31-12-2026"}'
```
**Response:**
```json
{"id":2,"title":"Spring Data JPA","description":"Master repositories","status":"PENDING","dueDate":"31-12-2026"}
```

---

### **Create Task 3**
```bash
curl -X POST http://localhost:8080/api/tasks -H "Content-Type: application/json" -d '{"title":"Task Manager Project","description":"Complete all features","dueDate":"31-12-2026"}'
```
**Response:**
```json
{"id":3,"title":"Task Manager Project","description":"Complete all features","status":"PENDING","dueDate":"31-12-2026"}
```

---

### **Mark Task as Completed**
```bash
curl -X PATCH http://localhost:8080/api/tasks/1/COMPLETED
```
**Response:**
```json
{"id":1,"title":"Spring Boot Learning","description":"Learn pagination","status":"COMPLETED","dueDate":"31-12-2026"}
```

---

### **Case 1: No Filters (Pagination + Sorting)**
```bash
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc"
```
**Response:**
```json
{
  "content": [
    {"id":1,"title":"Spring Boot Learning","description":"Learn pagination","status":"COMPLETED","dueDate":"31-12-2026"},
    {"id":2,"title":"Spring Data JPA","description":"Master repositories","status":"PENDING","dueDate":"31-12-2026"},
    {"id":3,"title":"Task Manager Project","description":"Complete all features","status":"PENDING","dueDate":"31-12-2026"}
  ],
  "totalElements": 3,
  "totalPages": 1,
  "first": true,
  "last": true,
  "size": 10,
  "number": 0
}
```

---

### **Case 2: Filter by PENDING Status**
```bash
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc&status=PENDING"
```
**Response:**
```json
{
  "content": [
    {"id":2,"title":"Spring Data JPA","description":"Master repositories","status":"PENDING","dueDate":"31-12-2026"},
    {"id":3,"title":"Task Manager Project","description":"Complete all features","status":"PENDING","dueDate":"31-12-2026"}
  ],
  "totalElements": 2,
  "totalPages": 1
}
```

---

### **Case 3: Filter by COMPLETED Status**
```bash
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc&status=COMPLETED"
```
**Response:**
```json
{
  "content": [
    {"id":1,"title":"Spring Boot Learning","description":"Learn pagination","status":"COMPLETED","dueDate":"31-12-2026"}
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

---

### **Case 4: Search by Title**
```bash
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc&search=Spring"
```
**Response:**
```json
{
  "content": [
    {"id":1,"title":"Spring Boot Learning","description":"Learn pagination","status":"COMPLETED","dueDate":"31-12-2026"},
    {"id":2,"title":"Spring Data JPA","description":"Master repositories","status":"PENDING","dueDate":"31-12-2026"}
  ],
  "totalElements": 2,
  "totalPages": 1
}
```

---

### **Case 5: Status + Search Together**
```bash
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=id&direction=asc&status=PENDING&search=Spring"
```
**Response:**
```json
{
  "content": [
    {"id":2,"title":"Spring Data JPA","description":"Master repositories","status":"PENDING","dueDate":"31-12-2026"}
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

---

### **Case 6: Pagination - Second Page**
```bash
curl "http://localhost:8080/api/tasks?page=1&size=2&sortBy=id&direction=asc"
```
**Response:**
```json
{
  "content": [
    {"id":3,"title":"Task Manager Project","description":"Complete all features","status":"PENDING","dueDate":"31-12-2026"}
  ],
  "pageable": {"pageNumber": 1, "pageSize": 2},
  "totalElements": 3,
  "totalPages": 2,
  "first": false,
  "last": true
}
```

---

### **Case 7: Sort by Title Ascending (A-Z)**
```bash
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=title&direction=asc"
```
**Response:**
```json
{
  "content": [
    {"id":1,"title":"Spring Boot Learning","status":"COMPLETED"},
    {"id":2,"title":"Spring Data JPA","status":"PENDING"},
    {"id":3,"title":"Task Manager Project","status":"PENDING"}
  ]
}
```

---

### **Case 8: Sort by Title Descending (Z-A)**
```bash
curl "http://localhost:8080/api/tasks?page=0&size=10&sortBy=title&direction=desc"
```
**Response:**
```json
{
  "content": [
    {"id":3,"title":"Task Manager Project","status":"PENDING"},
    {"id":2,"title":"Spring Data JPA","status":"PENDING"},
    {"id":1,"title":"Spring Boot Learning","status":"COMPLETED"}
  ]
}
```

---

### **Case 9: No Results (Search Non-existent)**
```bash
curl "http://localhost:8080/api/tasks?page=0&size=10&search=NonExistentKeyword"
```
**Response:**
```json
{
  "content": [],
  "empty": true,
  "totalElements": 0,
  "totalPages": 0
}
```

---

## 📊 **Quick Reference Table for README**

| Feature | Example URL |
|---------|-------------|
| **Pagination** | `/api/tasks?page=0&size=10` |
| **Sorting** | `/api/tasks?sortBy=title&direction=asc` |
| **Filter by Status** | `/api/tasks?status=PENDING` |
| **Search by Title** | `/api/tasks?search=Spring` |
| **All Combined** | `/api/tasks?page=0&size=5&sortBy=id&direction=asc&status=PENDING&search=Spring` |

---

## 🎯 **README Ready!**

Copy and paste these formatted outputs into your README. Your API documentation looks professional! 🚀