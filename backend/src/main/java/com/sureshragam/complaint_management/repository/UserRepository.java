package com.sureshragam.complaint_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sureshragam.complaint_management.model.User;

public interface UserRepository extends JpaRepository<User,Long >{

}
