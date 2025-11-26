package com.sureshragam.complaint_management.model;

import java.time.LocalDateTime;

import com.sureshragam.complaint_management.enums.EmployeeRole;

public class Employee {
	private Long id;
	private String name;
	private String email;
	private String password;
	private EmployeeRole role;
	private Long supervisor_id;
	private LocalDateTime created_at;
	private Boolean active;
	
}
