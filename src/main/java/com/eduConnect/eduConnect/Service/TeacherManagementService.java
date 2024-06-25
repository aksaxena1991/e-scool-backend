package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.StudentManagement;
import com.eduConnect.eduConnect.Model.TeacherManagement;
import com.eduConnect.eduConnect.Repositroy.TeacherManagementRepositroy;
import com.eduConnect.eduConnect.RespositroyImpl.TeacherManagementRepositroyImpl;

@Service
public class TeacherManagementService implements TeacherManagementRepositroyImpl{

	@Autowired
	private TeacherManagementRepositroy teacherManagementRepositroy;
	
	@Override
	public TeacherManagement saveTeacherManagement(TeacherManagement teacherManagement) throws Exception {
		if (teacherManagementRepositroy.existsByEmail(teacherManagement.getEmail())) {
			throw new DataIntegrityViolationException("Email address already exists");
		}

		teacherManagement.setTeacherRegDateTime(LocalDate.now());
		return teacherManagementRepositroy.save(teacherManagement);
	}

	@Override
	public TeacherManagement updatedTeacherManagement(TeacherManagement updatedTeacher, Long teacherId) throws Exception {
		TeacherManagement existingTeacher = teacherManagementRepositroy.findById(teacherId).orElse(null);
		if (existingTeacher == null) {
			throw new UserNotFoundException("Teacher not found");
		}

		if (updatedTeacher.getEmail() == null || updatedTeacher.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		}

		// Update school properties
		existingTeacher.setTeacherName(updatedTeacher.getTeacherName());
		existingTeacher.setEmail(updatedTeacher.getEmail());
		existingTeacher.setNumber(updatedTeacher.getNumber());
		existingTeacher.setAddress(updatedTeacher.getAddress());

		return teacherManagementRepositroy.save(existingTeacher);
	}
	
	@Override
	public List<TeacherManagement> getAllTeacherManagement() {
		return this.teacherManagementRepositroy.findAll();
	}

	@Override
	public TeacherManagement getTeacherManagementById(Long teacherId) {
		try {
			return teacherManagementRepositroy.findById(teacherId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteTeacherManagementById(Long teacherId) {
		this.teacherManagementRepositroy.deleteById(teacherId);
		
	}

	@Override
	public List<TeacherManagement> getTeacherManagementBySchoolId(Long schoolId) {
		List<TeacherManagement> teacherManagements = teacherManagementRepositroy.findBySchoolManagement_SchoolId(schoolId);
		return teacherManagements;
	}

}
