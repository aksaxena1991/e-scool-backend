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
@Table(name = "teacherManagement")
public class TeacherManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long teacherId;

	@Column(name = "teacher_req_date_time")
	private LocalDate teacherRegDateTime;

	@Column(name = "teacher_name")
	private String teacherName;

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

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public LocalDate getTeacherRegDateTime() {
		return teacherRegDateTime;
	}

	public void setTeacherRegDateTime(LocalDate teacherRegDateTime) {
		this.teacherRegDateTime = teacherRegDateTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
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
}
