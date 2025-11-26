# Complaint Management System (Monorepo)

A full-stack **Complaint Management System** built using:

- **Backend:** Spring Boot (Java 17+, MySQL)
- **Frontend (Consumer App):** React
- **Frontend (Employee App):** React
- **Architecture:** Clean layered structure
- **Deployment-ready:** GitHub Project Board, branching strategy, clear documentation

This system allows **users to submit complaints** and **employees/admins to manage and resolve them**.

---

# 📁 Repository Structure

```
complaint-management-system/
│
├── backend/                 # Spring Boot REST API
├── consumer-frontend/       # React App (Consumers)
├── employee-frontend/       # React App (Employees)
│
├── .github/                 # Issue templates + workflows
│   ├── ISSUE_TEMPLATE/
│   └── workflows/
│
└── README.md
```

---

# 🧬 System Architecture (Complete Explanation)

## 🔹 Backend — Spring Boot
- REST API  
- MySQL via JPA  
- Complaint lifecycle management  
- Role-based access control (future)  
- DTOs + Entities separation  

### Backend Layers:
```
Controller → Service → Repository → Entity → Database
```

---

## 🔹 Consumer Frontend — React
Used by **end users** to:
- Create complaints  
- View their complaint history  
- Track status  
- Add comments  

---

## 🔹 Employee Frontend — React
Used by **employees** to:
- View all complaints  
- Update complaint status  
- Add comments  
- Assign complaints (admin)  

---

## 🔹 MySQL Database
Normalized relational schema with:
- `users`
- `roles`
- `user_roles`
- `complaints`
- `complaint_comments`
- `complaint_attachments`
- `complaint_history`

---

# 🚀 Complete Setup Guide (All Steps in One)

## 1️⃣ Clone repo
```bash
git clone https://github.com/YOUR_USERNAME/complaint-management-system.git
cd complaint-management-system
```

---

## 2️⃣ Setup MySQL Database

```sql
CREATE DATABASE complaint_management_db;
```

(Optional DB user)

```sql
CREATE USER 'cms_user'@'%' IDENTIFIED BY 'cms_password';
GRANT ALL PRIVILEGES ON complaint_management_db.* TO 'cms_user'@'%';
```

---

## 3️⃣ Configure Backend

File:  
`backend/src/main/resources/application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/complaint_management_db?useSSL=false
    username: cms_user
    password: cms_password

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## 4️⃣ Run Backend
```
cd backend
./mvnw spring-boot:run
```

Backend runs at  
**http://localhost:8080**

---

## 5️⃣ Run Consumer Frontend
```
cd consumer-frontend
npm install
npm start
```

---

## 6️⃣ Run Employee Frontend
```
cd employee-frontend
npm install
npm start
```

---

# 📡 API Design (Complete Overview)

Base URL:
```
http://localhost:8080/api/v1
```

---

## 🔹 Complaints API

### ✔ Create complaint
```
POST /complaints
```

Request:
```json
{
  "title": "Internet issue",
  "description": "Not working since morning",
  "category": "NETWORK",
  "priority": "HIGH"
}
```

---

### ✔ Get my complaints (consumer)
```
GET /complaints/my
```

---

### ✔ Get all complaints (employee/admin)
```
GET /complaints
```

---

### ✔ Get complaint detail
```
GET /complaints/{id}
```

---

### ✔ Update status
```
PATCH /complaints/{id}/status
```

---

### ✔ Assign complaint
```
PATCH /complaints/{id}/assign
```

---

## 🔹 Comments API

### Add comment
```
POST /complaints/{id}/comments
```

---

# 🗄️ Database Schema (Complete)

## `users`
- id  
- username  
- email  
- password_hash  
- full_name  
- status  
- created_at  
- updated_at  

## `roles`
- id  
- name  

## `user_roles`
- user_id  
- role_id  

## `complaints`
- id  
- reference_number  
- title  
- description  
- category  
- priority  
- status  
- created_by  
- assigned_to  
- created_at  
- updated_at  

## `complaint_comments`
- id  
- complaint_id  
- author_id  
- message  
- created_at  

---

# 👥 Roles & Permissions (RBAC)

### Roles:
- CONSUMER  
- EMPLOYEE  
- ADMIN  

### Permission Matrix

| Action                | Consumer | Employee | Admin |
|-----------------------|:--------:|:--------:|:-----:|
| Create complaint      | ✔        | ✖        | ✔     |
| View my complaints    | ✔        | ✖        | ✔     |
| View all complaints   | ✖        | ✔        | ✔     |
| Update status         | ✖        | ✔        | ✔     |
| Add comment           | ✔        | ✔        | ✔     |
| Assign complaint      | ✖        | ✖        | ✔     |
| Manage users          | ✖        | ✖        | ✔     |

---

# 🌿 Branching Strategy

### Main Branches:
```
main → stable production code
dev  → development integration
```

### Feature Branches:
```
feature/backend/<module>
feature/frontend/consumer/<module>
feature/frontend/employee/<module>
```

### Bug Fix:
```
bugfix/<issue-number>-<desc>
```

### Docs:
```
docs/<topic>
```

### Hotfix:
```
hotfix/<desc>
```

---

# 🗂 GitHub Project Board (KANBAN)

### Columns:
- Backlog  
- To Do  
- In Progress  
- Review  
- Done  

### Automation:
- New issues → Backlog  
- Assigned → To Do  
- PR opened → In Progress  
- PR review → Review  
- PR merged → Done  

---

# 🛡️ Future Enhancements

- JWT authentication  
- File uploads  
- Admin analytics dashboard  
- Email notifications  
- Audit logs  
- Multi-language support  
- Realtime updates via WebSockets  

---

# 👨‍💻 Author

**Suresh Ragam**  
React + Java Full-Stack Developer  
