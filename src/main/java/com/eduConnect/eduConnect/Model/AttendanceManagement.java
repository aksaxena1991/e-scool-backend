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

@Entity
@Table(name = "attendanceManagement")
public class AttendanceManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attendanceId;

	@Column(name = "attendance_date_time")
	private LocalDate attendanceDateTime;

	@Column(name = "attendance")
	private Boolean attendance;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "class_name")
	private String className;
	
	@ManyToOne(fetch = FetchType.EAGER) // Eager loading
	@JoinColumn(name = "schoolId")
	private SchoolManagement schoolManagement;
	
	@ManyToOne(fetch = FetchType.EAGER) // Eager loading
	@JoinColumn(name = "classId")
	private ClassesManagement classesManagement;
	
	@ManyToOne(fetch = FetchType.EAGER) // Eager loading
	@JoinColumn(name = "subjectId")
	private SubjectManagement subjectManagement;
	
	@ManyToOne(fetch = FetchType.EAGER) // Eager loading
	@JoinColumn(name = "studentId")
	private StudentManagement studentManagement;

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public LocalDate getAttendanceDateTime() {
		return attendanceDateTime;
	}

	public void setAttendanceDateTime(LocalDate attendanceDateTime) {
		this.attendanceDateTime = attendanceDateTime;
	}

	public Boolean getAttendance() {
		return attendance;
	}

	public void setAttendance(Boolean attendance) {
		this.attendance = attendance;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public SchoolManagement getSchoolManagement() {
		return schoolManagement;
	}

	public void setSchoolManagement(SchoolManagement schoolManagement) {
		this.schoolManagement = schoolManagement;
	}

	public ClassesManagement getClassesManagement() {
		return classesManagement;
	}

	public void setClassesManagement(ClassesManagement classesManagement) {
		this.classesManagement = classesManagement;
	}

	public SubjectManagement getSubjectManagement() {
		return subjectManagement;
	}

	public void setSubjectManagement(SubjectManagement subjectManagement) {
		this.subjectManagement = subjectManagement;
	}

	public StudentManagement getStudentManagement() {
		return studentManagement;
	}

	public void setStudentManagement(StudentManagement studentManagement) {
		this.studentManagement = studentManagement;
	}
}
