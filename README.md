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
