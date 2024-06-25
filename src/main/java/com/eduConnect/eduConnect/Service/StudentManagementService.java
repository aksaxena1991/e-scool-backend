package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.SchoolManagement;
import com.eduConnect.eduConnect.Model.StudentManagement;
import com.eduConnect.eduConnect.Repositroy.SchoolManagementRespositroy;
import com.eduConnect.eduConnect.Repositroy.StudentManagementRespositroy;
import com.eduConnect.eduConnect.RespositroyImpl.StudentManagementRespositroyImpl;


@Service
public class StudentManagementService implements StudentManagementRespositroyImpl {

	@Autowired
	private StudentManagementRespositroy studentManagementRespositroy;

	@Autowired
	private SchoolManagementRespositroy schoolManagementRespositroy;
	
	@Override
	public StudentManagement saveStudentManagement(StudentManagement studentManagement) throws Exception {
		
		 // Validate SchoolId is provided
        if (studentManagement.getSchoolManagement() == null || studentManagement.getSchoolManagement().getSchoolId() == null) {
            throw new UserNotFoundException("School ID is missing");
        }

        // Fetch the School from the database
        SchoolManagement schoolManagement = schoolManagementRespositroy.findById(studentManagement.getSchoolManagement().getSchoolId())
                                  .orElseThrow(() -> new Exception("School not found"));
        
		if (studentManagementRespositroy.existsByEmail(studentManagement.getEmail())) {
			throw new DataIntegrityViolationException("Email address already exists");
		}

		// Set school and current date
		studentManagement.setSchoolManagement(schoolManagement);
		studentManagement.setStudentRegDateTime(LocalDate.now());
		return studentManagementRespositroy.save(studentManagement);
	}
	
	@Override
	public StudentManagement updatedStudentManagement(StudentManagement updatedStudent, Long studentId) throws Exception {
		StudentManagement existingStudent = studentManagementRespositroy.findById(studentId).orElse(null);
		if (existingStudent == null) {
			throw new UserNotFoundException("Student not found");
		}

		if (updatedStudent.getEmail() == null || updatedStudent.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		}

		// Update school properties
		existingStudent.setStudentName(updatedStudent.getStudentName());
		existingStudent.setEmail(updatedStudent.getEmail());
		existingStudent.setNumber(updatedStudent.getNumber());
		existingStudent.setAddress(updatedStudent.getAddress());

		return studentManagementRespositroy.save(existingStudent);
	}

	@Override
	public List<StudentManagement> getAllStudentManagement() {
		return this.studentManagementRespositroy.findAll();
	}

	@Override
	public StudentManagement getStudentManagementById(Long studentId) {
		try {
			return studentManagementRespositroy.findById(studentId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteStudentManagementById(Long studentId) {
		this.studentManagementRespositroy.deleteById(studentId);
	}

	@Override
	public List<StudentManagement> getStudentManagementBySchoolId(Long schoolId) {
		List<StudentManagement> studentManagements = studentManagementRespositroy.findBySchoolManagement_SchoolId(schoolId);
		return studentManagements;
	}

}
