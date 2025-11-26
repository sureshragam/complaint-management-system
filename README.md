# Complaint Management System (Monorepo)

A full-stack **Complaint Management System** built using:

- **Backend:** Spring Boot (Java 17+, MySQL)
- **Frontend (Consumer App):** React
- **Frontend (Employee App):** React
- **Architecture:** Clean layered structure
- **Deployment-ready:** GitHub Project Board, branching strategy, clear documentation

This system allows **users to submit complaints** and **employees/admins to manage and resolve them**.

---

# Complaint Management System – Project Overview (Monorepo)

This document provides a **complete high‑level overview of your entire monorepo**, including:

- System architecture
- Technology stack
- Module-level responsibilities
- Repository structure
- How backend, frontends, and database interact
- Deployment vision
- Future expansion roadmap

This file is meant to serve as the **master documentation entry point** for the entire project.

---

# 1️⃣ System Architecture (High-Level)

```
[ Consumer React App ]        [ Employee React App ]
          |                             |
          |      HTTPS (JSON REST API)  |
          +--------------+--------------+
                         |
                [ Spring Boot Backend ]
                         |
        +----------------+------------------+
        |                                   |
 [ Relational DB (MySQL/Postgres) ]    [ File Storage ]
```

---

## 🔐 Authentication Model

All frontends communicate with backend using **JWT Authentication**.

Roles encoded inside JWT:

- **ROLE_USER** → Consumer / Customer
- **ROLE_WORKER** → Field Technician
- **ROLE_SUPERVISOR** → Team Lead
- **ROLE_ADMIN** → Super Admin

Backend enforces RBAC using:

```
@PreAuthorize("hasRole('WORKER')")
@PreAuthorize("hasRole('SUPERVISOR')")
@PreAuthorize("hasRole('ADMIN')")
```

---

# 2️⃣ Technology Stack

### **Frontend**

- React (Consumer App)
- React (Employee App)
- Axios for API requests
- React Router
- Tailwind / CSS modules (your choice)

---

### **Backend (Spring Boot + Java)**

- Spring Web
- Spring Data JPA
- Spring Security (JWT)
- Hibernate
- Validation API
- Lombok (optional)
- Swagger / OpenAPI

---

### **Database**

- MySQL (recommended)
- PostgreSQL (optional alternative)

---

### **Storage**

- Local file system (`uploads/complaints/...`)
- Future option: AWS S3 bucket

---

# 3️⃣ Repository Structure (Monorepo Design)

```
complaint-management-system/
│
├── backend/                 # Spring Boot API
│
├── consumer-frontend/       # React app for customers
│
├── employee-frontend/       # React app for employees
│
├── docs/
│   ├── backend/
│   │    ├── backend-architecture.md
│   │    ├── api-design.md
│   │    ├── db-schema.md
│   ├── frontend/
│   │    ├── consumer-ui.md
│   │    ├── employee-ui.md
│   ├── project/
│        ├── overall-architecture.md
│        ├── contribution-guide.md
│        ├── setup-guide.md
│
└── README.md
```

---

# 4️⃣ Core Modules & Responsibilities

## **A. Consumer Frontend**

- User registration/login
- Raise new complaints
- Upload images
- Track complaint status
- Add comments
- View history

---

## **B. Employee Frontend**

- Login with employee role
- View OPEN/ASSIGNED complaints
- Assign to self
- Update progress
- Add internal comments
- Supervisor/Admin:
  - Assign complaints to workers
  - Manage employees
  - Reject complaints

---

## **C. Backend (Spring Boot)**

### Main Responsibilities:

- JWT Authentication (user + employee login flows)
- Complaint lifecycle engine
- Role-based API protection
- Image upload handling
- Database CRUD operations
- Supervisor-level assignment workflows
- Admin-level management

---

# 5️⃣ Module-to-Module Interaction Flow

### **Consumer complains → Backend → Employee → Backend → Consumer**

```
User → POST /complaints → Backend → DB
Employee → GET /employee/complaints → Backend → DB
Employee updates → PATCH /status → Backend → DB
User tracks → GET /complaints/my → Backend → DB
```

Everything flows through a **central Spring Boot API**.

---

# 7️⃣ Deployment Vision

### Backend Deployment Options:

- AWS EC2
- AWS Elastic Beanstalk
- Dockerized spring boot app
- Future: Kubernetes (AKS/EKS)

---

### Frontend Deployment:

- Vercel / Netlify
- S3 + CloudFront
- GitHub Pages (development only)

---

### Database:

- AWS RDS MySQL
- Azure MySQL server

---

# 8️⃣ Future Roadmap

### Planned improvements:

- Real-time notifications (WebSockets)
- Mobile app (React Native)
- Worker location tracking
- Customer feedback system
- Supervisor analytics dashboard
- Automated SLA reminders

---

# 9️⃣ Summary

This document is the **master overview of the whole project**.  
Every module (backend/frontend/database) connects through the central architecture described here.

For deeper details, refer to:

- Backend Architecture
- API Design
- DB Schema
- Contribution Guidelines
- Setup Instructions

This overview ensures:

- Clean project design
- Clear developer onboarding
- Scalable architecture
- Professional documentation structure

---

**Author:**  
**Suresh Ragam** – React + Java Full-Stack Developer

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

| Action              | Consumer | Employee | Admin |
| ------------------- | :------: | :------: | :---: |
| Create complaint    |    ✔     |    ✖     |   ✔   |
| View my complaints  |    ✔     |    ✖     |   ✔   |
| View all complaints |    ✖     |    ✔     |   ✔   |
| Update status       |    ✖     |    ✔     |   ✔   |
| Add comment         |    ✔     |    ✔     |   ✔   |
| Assign complaint    |    ✖     |    ✖     |   ✔   |
| Manage users        |    ✖     |    ✖     |   ✔   |

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
