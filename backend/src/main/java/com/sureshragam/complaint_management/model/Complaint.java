package com.sureshragam.complaint_management.model;

import java.time.LocalDateTime;
import java.util.List;

import com.sureshragam.complaint_management.enums.ComplaintStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Complaint")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String product_type;
	private String product_model;
	private String purchased_from;
	private LocalDateTime purchase_date;
	private String issue_description;
	@Enumerated(EnumType.STRING)
	private ComplaintStatus status = ComplaintStatus.OPEN;
	@ManyToOne
	@JoinColumn(name="assigned_to")
	private Employee assignedToEmployee;
	
	@ManyToOne
	@JoinColumn(name="assigned_by")
	private Employee assignedByEmplyoee;
	
	
	private String reject_reason;
	private LocalDateTime created_at = LocalDateTime.now();
	private LocalDateTime updated_at= LocalDateTime.now();;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy= "complaint",cascade=CascadeType.ALL)
	private List<ComplaintImage> images;
	
	@OneToMany(mappedBy="comment",cascade=CascadeType.ALL)
	private List<ComplaintComment> comments;
	
	
	

	
}
