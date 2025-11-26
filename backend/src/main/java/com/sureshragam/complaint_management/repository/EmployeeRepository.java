package com.sureshragam.complaint_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sureshragam.complaint_management.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
