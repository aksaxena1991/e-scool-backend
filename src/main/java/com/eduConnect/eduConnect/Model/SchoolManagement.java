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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "schoolManagement")
public class SchoolManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long schoolId;

	@Column(name = "school_reg_date_time")
	private LocalDate schoolRegDateTime;

	@Column(name = "school_name")
	private String schoolName;

	@Column(unique = true)
	@NotEmpty(message = "Email must not be empty")
	private String email;

	@Column(name = "number")
	private String number;

	@Column(name = "address")
	private String address;

	@OneToMany(mappedBy = "schoolManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<StudentManagement> students;

    @OneToMany(mappedBy = "schoolManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TeacherManagement> teachers;
    
    @OneToMany(mappedBy = "schoolManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ParentManagement> parent;
    
    @OneToMany(mappedBy = "schoolManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AttendanceManagement> attendance;
    
    @OneToMany(mappedBy = "schoolManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ClassesManagement> classes;

    @OneToMany(mappedBy = "schoolManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SubjectManagement> subject;
    
	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public LocalDate getSchoolRegDateTime() {
		return schoolRegDateTime;
	}

	public void setSchoolRegDateTime(LocalDate schoolRegDateTime) {
		this.schoolRegDateTime = schoolRegDateTime;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
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

	public List<StudentManagement> getStudents() {
		return students;
	}

	public void setStudents(List<StudentManagement> students) {
		this.students = students;
	}

	public List<TeacherManagement> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherManagement> teachers) {
		this.teachers = teachers;
	}

	public List<ParentManagement> getParent() {
		return parent;
	}

	public void setParent(List<ParentManagement> parent) {
		this.parent = parent;
	}

	public List<AttendanceManagement> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<AttendanceManagement> attendance) {
		this.attendance = attendance;
	}

	public List<ClassesManagement> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassesManagement> classes) {
		this.classes = classes;
	}

	public List<SubjectManagement> getSubject() {
		return subject;
	}

	public void setSubject(List<SubjectManagement> subject) {
		this.subject = subject;
	}
}
