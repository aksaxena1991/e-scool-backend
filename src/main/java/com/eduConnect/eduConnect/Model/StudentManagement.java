package com.eduConnect.eduConnect.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "studentManagement")
public class StudentManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;

	@Column(name = "student_req_date_time")
	private LocalDate studentRegDateTime;

	@Column(name = "student_name")
	private String studentName;

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

	@OneToMany(mappedBy = "studentManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ParentManagement> parent;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public LocalDate getStudentRegDateTime() {
		return studentRegDateTime;
	}

	public void setStudentRegDateTime(LocalDate studentRegDateTime) {
		this.studentRegDateTime = studentRegDateTime;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public List<ParentManagement> getParent() {
		return parent;
	}

	public void setParent(List<ParentManagement> parent) {
		this.parent = parent;
	}

}
