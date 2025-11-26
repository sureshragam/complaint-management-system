package com.sureshragam.complaint_management.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(unique=true)
	private String email;
	private String password;
	private String phone;
	private LocalDateTime created_at = LocalDateTime.now();
	private Boolean active=true;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Complaint> complaints;
	
}
