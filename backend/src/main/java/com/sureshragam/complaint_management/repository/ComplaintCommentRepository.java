package com.sureshragam.complaint_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sureshragam.complaint_management.model.ComplaintComment;

public interface ComplaintCommentRepository extends JpaRepository<ComplaintComment, Long> {

}
