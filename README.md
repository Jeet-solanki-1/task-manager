## 6. Task Manager (To-Do List) ✅
Goal: Simple CRUD for tasks with status (pending/completed) and due dates.

Features:

GET /api/tasks – list all tasks.

POST /api/tasks – create a task (title, description, dueDate).

PUT /api/tasks/{id} – update task (mark complete, change due date).

DELETE /api/tasks/{id} – delete task.

Filter by status: GET /api/tasks?status=pending.

Learn: Basic JPA entities, @RestController, @Repository, pagination.

Frontend: Simple list with add/edit/delete buttons. --> i dont need it.


# At the end of this project i will remake my generic-service that i made in inventory hub.