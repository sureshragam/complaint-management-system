# Backend Architecture & Implementation Guide

This document provides a complete backend architecture, database schema, API design, folder structure, and step‑by‑step implementation guide for the Complaint Management System.

---

# 1️⃣ System Architecture (High-Level)

```
[ Consumer React App ]        [ Employee React App ]
          |                             |
          |  (HTTPS, JSON REST API)     |
          +-------------+---------------+
                        |
                [ Spring Boot API ]
                        |
        +----------------+------------------+
        |                                   |
 [ Relational DB (MySQL / Postgres) ]   [ File Storage ]
```

### Authentication Style

All frontends communicate with backend using **JWT**.

Roles encoded in JWT:

- **ROLE_USER** → Consumer (customer)
- **ROLE_WORKER** → Field employee
- **ROLE_SUPERVISOR**
- **ROLE_ADMIN**

---

# 2️⃣ Database Schema (Relational: MySQL/Postgres)

Below are the core tables needed for V1.

---

## 🔹 1. users (customers)

```
id BIGINT PK
full_name
email (unique)
password_hash
phone
created_at
updated_at
role = ROLE_USER
```

---

## 🔹 2. employees

```
id BIGINT PK
full_name
email (unique)
password_hash
phone
role = ROLE_WORKER / ROLE_SUPERVISOR / ROLE_ADMIN
status = ACTIVE / DISABLED
created_at
updated_at
```

---

## 🔹 3. complaints

```
id BIGINT PK
user_id FK → users.id
assigned_to FK → employees.id (nullable)
product_type
issue_description
purchased_from
status = OPEN / ASSIGNED / IN_PROGRESS / OUTCOME / REJECTED / CLOSED
created_at
updated_at
```

---

## 🔹 4. complaint_images

```
id BIGINT PK
complaint_id FK → complaints.id
file_path
uploaded_by (USER or EMPLOYEE)
created_at
```

---

## 🔹 5. complaint_comments

```
id BIGINT PK
complaint_id FK
author_type = USER / EMPLOYEE
author_id
comment_text
created_at
```

---

# 3️⃣ API Design (Complete)

The full REST API grouped by module and role.

---

## A. 🔐 Auth APIs (Common)

### **Customer Signup**

```
POST /api/auth/user/signup
```

### **Customer Login**

```
POST /api/auth/user/login
```

Returns JWT with **ROLE_USER**

---

### **Employee Login**

```
POST /api/auth/employee/login
```

Returns JWT with **ROLE_WORKER / ROLE_SUPERVISOR / ROLE_ADMIN**

---

# B. 👤 Consumer APIs (ROLE_USER)

### 1. Get my profile

```
GET /api/users/me
```

### 2. Create complaint

```
POST /api/complaints
```

### 3. Get my complaints

```
GET /api/complaints/my
```

### 4. Get complaint detail

```
GET /api/complaints/{id}
```

(Only allowed if complaint belongs to logged‑in user)

---

### Image Upload

```
POST /api/complaints/{id}/images
Content-Type: multipart/form-data
```

### Add Comment

```
POST /api/complaints/{id}/comments
```

---

# C. 🧑‍🔧 Employee – Common APIs

Accessible to **ROLE_WORKER, ROLE_SUPERVISOR, ROLE_ADMIN**

### Get employee profile

```
GET /api/employee/me
```

### Get list of complaints

```
GET /api/employee/complaints?status=OPEN&page=0&size=20
```

### Get complaint detail

```
GET /api/employee/complaints/{id}
```

### Add internal comment

```
POST /api/employee/complaints/{id}/comments
```

### Employee uploads image

```
POST /api/employee/complaints/{id}/images
```

---

# D. 👷 Worker APIs (ROLE_WORKER)

### Assign complaint to self

```
POST /api/employee/complaints/{id}/assign-self
```

### Update status

```
POST /api/employee/complaints/{id}/status
```

Only if worker is assigned to that complaint.

---

# E. 🧑‍💼 Supervisor APIs (ROLE_SUPERVISOR, ROLE_ADMIN)

### Create worker account

```
POST /api/employee/workers
```

### Get all workers

```
GET /api/employee/workers
```

### Assign complaint to any worker

```
POST /api/employee/complaints/{id}/assign/{workerId}
```

---

# F. 🛠 Admin APIs (ROLE_ADMIN)

### Create supervisors

```
POST /api/employee/supervisors
```

### List supervisors

```
GET /api/employee/supervisors
```

### List all employees

```
GET /api/employee/all
```

### Reject complaint

```
POST /api/complaints/{id}/reject
```

### Delete complaint

```
DELETE /api/complaints/{id}
```

---

# 4️⃣ Backend Folder Structure (Spring Boot)

```
backend/
  src/main/java/com/project/complaints/

    config/
      SwaggerConfig.java
      WebConfig.java

    security/
      JwtAuthenticationFilter.java
      JwtTokenProvider.java
      SecurityConfig.java
      CustomUserDetailsService.java

    controller/
      auth/
        UserAuthController.java
        EmployeeAuthController.java

      user/
        UserProfileController.java
        UserComplaintController.java

      employee/
        EmployeeComplaintController.java
        WorkerController.java
        SupervisorController.java
        AdminController.java

    dto/
      auth/
        LoginRequest.java
        SignupRequest.java
        JwtResponse.java

      complaint/
        ComplaintRequest.java
        ComplaintResponse.java
        CommentRequest.java
        CommentResponse.java
        ImageResponse.java

      user/
        UserDto.java

      employee/
        EmployeeDto.java

    entity/
      User.java
      Employee.java
      Complaint.java
      ComplaintImage.java
      ComplaintComment.java

      enums/
        ComplaintStatus.java
        EmployeeRole.java
        AuthorType.java

    repository/
      UserRepository.java
      EmployeeRepository.java
      ComplaintRepository.java
      ComplaintImageRepository.java
      ComplaintCommentRepository.java

    service/
      interfaces/
        AuthService.java
        UserComplaintService.java
        EmployeeComplaintService.java
        EmployeeService.java

      impl/
        AuthServiceImpl.java
        UserComplaintServiceImpl.java
        EmployeeComplaintServiceImpl.java
        EmployeeServiceImpl.java

    exception/
      ResourceNotFoundException.java
      UnauthorizedException.java
      GlobalExceptionHandler.java

    util/
      FileStorageService.java
      MapperUtil.java

  src/main/resources/
    application.yml
```

---

# 5️⃣ Step‑By‑Step Implementation Plan

This is your roadmap to build the backend cleanly.

---

## ✅ **Step 1: Create Spring Boot project**

Add dependencies:

- Spring Web
- Spring Security
- Spring Data JPA
- MySQL / PostgreSQL driver
- Validation
- Lombok
- Springdoc/Swagger
- AWS SDK (optional for S3)

---

## ✅ **Step 2: Create Entities + Repositories**

Implement:

- User
- Employee
- Complaint
- ComplaintImage
- ComplaintComment

Then create JPA repositories.

---

## ✅ **Step 3: Security + JWT**

- Add EmployeeRole enum
- Add ROLE_USER
- Implement CustomUserDetailsService
- Implement JwtTokenProvider
- Implement JwtAuthenticationFilter
- Add SecurityConfig with `@PreAuthorize` support
- Allow: `/api/auth/**`
- Secure: all other APIs

---

## ✅ **Step 4: Auth APIs**

Implement:

- `/auth/user/signup`
- `/auth/user/login`
- `/auth/employee/login`

Return:

- JWT
- role
- basic profile

---

## ✅ **Step 5: User Complaint Flow**

Implement UserComplaintController:

- POST /complaints
- GET /complaints/my
- GET /complaints/{id}

Map DTOs → Entities.

---

## ✅ **Step 6: Image Upload Feature**

Implement:

- FileStorageService
- Save files in `/uploads/complaints/{id}`
- Save DB entry in ComplaintImage

Controller:

- POST `/complaints/{id}/images`

---

## ✅ **Step 7: Comments**

Implement:

- ComplaintComment entity
- Repositories
- User + Employee comment endpoints

---

## ✅ **Step 8: Employee Complaint Workflow**

EmployeeComplaintController:

- List complaints
- Assign self
- Supervisor assigns worker
- Worker updates status

Use:

```
@PreAuthorize("hasRole('WORKER')")
@PreAuthorize("hasRole('SUPERVISOR')")
@PreAuthorize("hasRole('ADMIN')")
```

---

## ✅ **Step 9: Admin APIs**

Add AdminController:

- Manage employees
- Delete complaints
- Reject complaints

---

## ✅ **Step 10: Final Touches**

- Add validation annotations
- Implement GlobalExceptionHandler
- Enable Swagger
- Add logs
- Pagination, sorting
- Testing via Postman

---

# 🎉 Backend Architecture Completed

This is your **complete backend blueprint** for the Complaint Management System.

Use this MD file for GitHub or internal documentation.
