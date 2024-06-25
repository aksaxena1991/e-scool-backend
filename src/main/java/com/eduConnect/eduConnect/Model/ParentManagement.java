package com.eduConnect.eduConnect.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "parentManagement")
public class ParentManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long parentId;

	@Column(name = "parent_req_date_time")
	private LocalDate parentRegDateTime;

	@Column(name = "parent_name")
	private String parentName;

	@Column(unique = true)
	@NotEmpty(message = "Email must not be empty")
	private String email;

	@Column(name = "number")
	private String number;

	@Column(name = "address")
	private String address;

	@ManyToOne(fetch = FetchType.EAGER) // Eager loading
	@JoinColumn(name = "schoolId")
	private SchoolManagement schoolManagement;
	
	@ManyToOne(fetch = FetchType.EAGER) // Eager loading
	@JoinColumn(name = "studentId")
	private StudentManagement studentManagement;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public LocalDate getParentRegDateTime() {
		return parentRegDateTime;
	}

	public void setParentRegDateTime(LocalDate parentRegDateTime) {
		this.parentRegDateTime = parentRegDateTime;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public SchoolManagement getSchoolManagement() {
		return schoolManagement;
	}

	public void setSchoolManagement(SchoolManagement schoolManagement) {
		this.schoolManagement = schoolManagement;
	}

	public StudentManagement getStudentManagement() {
		return studentManagement;
	}

	public void setStudentManagement(StudentManagement studentManagement) {
		this.studentManagement = studentManagement;
	}
	
}
