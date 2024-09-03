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
@Table(name = "classManagement")
public class ClassesManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classId;

	@Column(name = "class_add_date_time")
	private LocalDate classAddDateTime;

	@Column(name = "class_name")
	private String className;

	@Column(name = "class_section")
	private String classSection;
	
	@ManyToOne(fetch = FetchType.EAGER) // Eager loading
	@JoinColumn(name = "schoolId")
	private SchoolManagement schoolManagement;
	
//	@OneToMany(mappedBy = "classManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JsonIgnore
//	private List<AttendanceManagement> attendance;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public LocalDate getClassAddDateTime() {
		return classAddDateTime;
	}

	public void setClassAddDateTime(LocalDate classAddDateTime) {
		this.classAddDateTime = classAddDateTime;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassSection() {
		return classSection;
	}

	public void setClassSection(String classSection) {
		this.classSection = classSection;
	}

	public SchoolManagement getSchoolManagement() {
		return schoolManagement;
	}

	public void setSchoolManagement(SchoolManagement schoolManagement) {
		this.schoolManagement = schoolManagement;
	}

//	public List<AttendanceManagement> getAttendance() {
//		return attendance;
//	}
//
//	public void setAttendance(List<AttendanceManagement> attendance) {
//		this.attendance = attendance;
//	}
	
}
