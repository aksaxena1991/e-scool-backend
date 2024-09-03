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

@Entity
@Table(name = "subjectManagement")
public class SubjectManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subjectId;

	@Column(name = "subject_add_date_time")
	private LocalDate subjectAddDateTime;

	@Column(name = "subject_name")
	private String subjectName;

	@ManyToOne(fetch = FetchType.EAGER) // Eager loading
	@JoinColumn(name = "schoolId")
	private SchoolManagement schoolManagement;
	
	@OneToMany(mappedBy = "subjectManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<AttendanceManagement> attendance;

	
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public LocalDate getSubjectAddDateTime() {
		return subjectAddDateTime;
	}

	public void setSubjectAddDateTime(LocalDate subjectAddDateTime) {
		this.subjectAddDateTime = subjectAddDateTime;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public SchoolManagement getSchoolManagement() {
		return schoolManagement;
	}

	public void setSchoolManagement(SchoolManagement schoolManagement) {
		this.schoolManagement = schoolManagement;
	}

	public List<AttendanceManagement> getAttendance() {
		return attendance;
	}

	public void setAttendance(List<AttendanceManagement> attendance) {
		this.attendance = attendance;
	}
	
}
