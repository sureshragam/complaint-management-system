package com.sureshragam.complaint_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.sureshragam.complaint_management.model.ComplaintImage;

public interface ComplaintImageRepository extends JpaRepository<ComplaintImage,Long>{

}
