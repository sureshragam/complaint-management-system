package com.sureshragam.complaint_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sureshragam.complaint_management.model.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

}
