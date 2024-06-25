package com.eduConnect.eduConnect.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
