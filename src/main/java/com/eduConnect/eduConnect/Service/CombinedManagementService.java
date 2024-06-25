package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Dto.CombinedParentManagementDto;
import com.eduConnect.eduConnect.Dto.CombinedSchoolManagementDto;
import com.eduConnect.eduConnect.Dto.CombinedStudentManagementDto;
import com.eduConnect.eduConnect.Dto.CombinedTeacherManagementDto;
import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.ParentManagement;
import com.eduConnect.eduConnect.Model.SchoolManagement;
import com.eduConnect.eduConnect.Model.StudentManagement;
import com.eduConnect.eduConnect.Model.TeacherManagement;
import com.eduConnect.eduConnect.Model.UsersManagement;
import com.eduConnect.eduConnect.Repositroy.ParentManagementRespostory;
import com.eduConnect.eduConnect.Repositroy.SchoolManagementRespositroy;
import com.eduConnect.eduConnect.Repositroy.StudentManagementRespositroy;
import com.eduConnect.eduConnect.Repositroy.TeacherManagementRepositroy;
import com.eduConnect.eduConnect.Repositroy.UsersManageRepositroy;

@Service
public class CombinedManagementService {

	@Autowired
	private UsersManageRepositroy usersManageRepositroy;

	@Autowired
	private SchoolManagementRespositroy schoolManagementRepository;
	
	@Autowired
	private TeacherManagementRepositroy teacherManagementRepositroy;
	
	@Autowired
	private StudentManagementRespositroy studentManagementRespositroy;

	@Autowired
	private ParentManagementRespostory parentManagementRespostory;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void saveCombinedSchoolManagement(CombinedSchoolManagementDto combinedSchoolManagementDto) throws Exception {
		UsersManagement usersManagement = combinedSchoolManagementDto.getUsersManagement();
		SchoolManagement schoolManagement = combinedSchoolManagementDto.getSchoolManagement();

		// Validate and save UsersManagement
		if (usersManageRepositroy.existsByEmail(usersManagement.getEmail())) {
			throw new DataIntegrityViolationException("User email address already exists");
		}
		usersManagement.setUsersAddDateTime(LocalDate.now());
		usersManagement.setPassword(passwordEncoder.encode(usersManagement.getPassword()));
		usersManageRepositroy.save(usersManagement);

		// Validate and save SchoolManagement
		if (schoolManagementRepository.existsByEmail(schoolManagement.getEmail())) {
			throw new DataIntegrityViolationException("School email address already exists");
		}
		schoolManagement.setSchoolRegDateTime(LocalDate.now());
		schoolManagementRepository.save(schoolManagement);
	}
	
	public void saveCombinedTeacherManagement(CombinedTeacherManagementDto combinedTeacherManagementDto) throws Exception {
		UsersManagement usersManagement = combinedTeacherManagementDto.getUsersManagement();
		TeacherManagement teacherManagement = combinedTeacherManagementDto.getTeacherManagement();
		
		// Validate and save UsersManagement
		if (usersManageRepositroy.existsByEmail(usersManagement.getEmail())) {
			throw new DataIntegrityViolationException("User email address already exists");
		}
		usersManagement.setUsersAddDateTime(LocalDate.now());
		usersManagement.setPassword(passwordEncoder.encode(usersManagement.getPassword()));
		usersManageRepositroy.save(usersManagement);

		 // Validate SchoolId is provided
	    if (teacherManagement.getSchoolManagement() == null || teacherManagement.getSchoolManagement().getSchoolId() == null) {
	        throw new UserNotFoundException("School ID is missing");
	    }

	    // Fetch the School from the database
	    SchoolManagement schoolManagement = schoolManagementRepository.findById(teacherManagement.getSchoolManagement().getSchoolId())
	                              .orElseThrow(() -> new Exception("School ID not found"));
		// Validate and save teacherManagement
		if (teacherManagementRepositroy.existsByEmail(teacherManagement.getEmail())) {
			throw new DataIntegrityViolationException("Teacher email address already exists");
		}
		
	    // Set school and current date
		teacherManagement.setSchoolManagement(schoolManagement);
		teacherManagement.setTeacherRegDateTime(LocalDate.now());
		teacherManagementRepositroy.save(teacherManagement);
	}

	public void saveCombinedStudentManagement(CombinedStudentManagementDto combinedStudentManagementDto) throws Exception {
	    UsersManagement usersManagement = combinedStudentManagementDto.getUsersManagement();
	    StudentManagement studentManagement = combinedStudentManagementDto.getStudentManagement();

	    // Validate and save UsersManagement
	    if (usersManageRepositroy.existsByEmail(usersManagement.getEmail())) {
	        throw new DataIntegrityViolationException("User email address already exists");
	    }
	    usersManagement.setUsersAddDateTime(LocalDate.now());
	    usersManagement.setPassword(passwordEncoder.encode(usersManagement.getPassword()));
	    usersManageRepositroy.save(usersManagement);

	    // Validate SchoolId is provided
	    if (studentManagement.getSchoolManagement() == null || studentManagement.getSchoolManagement().getSchoolId() == null) {
	        throw new UserNotFoundException("School ID is missing");
	    }

	    // Fetch the School from the database
	    SchoolManagement schoolManagement = schoolManagementRepository.findById(studentManagement.getSchoolManagement().getSchoolId())
	                              .orElseThrow(() -> new Exception("School ID not found"));

	    // Validate and save studentManagement
	    if (studentManagementRespositroy.existsByEmail(studentManagement.getEmail())) {
	        throw new DataIntegrityViolationException("Student email address already exists");
	    }
	    
	    // Set school and current date
	    studentManagement.setSchoolManagement(schoolManagement);
	    studentManagement.setStudentRegDateTime(LocalDate.now());
	    studentManagementRespositroy.save(studentManagement);
	}

	public void saveCombinedParentManagement(CombinedParentManagementDto combinedParentManagementDto) throws Exception {
	    UsersManagement usersManagement = combinedParentManagementDto.getUsersManagement();
	    ParentManagement parentManagement =  combinedParentManagementDto.getParentManagement();

	    // Validate and save UsersManagement
	    if (usersManageRepositroy.existsByEmail(usersManagement.getEmail())) {
	        throw new DataIntegrityViolationException("User email address already exists");
	    }
	    usersManagement.setUsersAddDateTime(LocalDate.now());
	    usersManagement.setPassword(passwordEncoder.encode(usersManagement.getPassword()));
	    usersManageRepositroy.save(usersManagement);

	    // Validate SchoolId is provided
        if (parentManagement.getSchoolManagement() == null || parentManagement.getSchoolManagement().getSchoolId() == null) {
            throw new UserNotFoundException("School ID is missing");
        }

        // Fetch the School from the database
        SchoolManagement schoolManagement = schoolManagementRepository.findById(parentManagement.getSchoolManagement().getSchoolId())
                                  .orElseThrow(() -> new Exception("School not found"));
        
        
        // Validate StudentId is provided
        if (parentManagement.getStudentManagement() == null || parentManagement.getStudentManagement().getStudentId() == null) {
            throw new UserNotFoundException("Student ID is missing");
        }

        // Fetch the School from the database
        StudentManagement studentManagement = studentManagementRespositroy.findById(parentManagement.getStudentManagement().getStudentId())
                                  .orElseThrow(() -> new Exception("Student not found"));
        
        
		if (parentManagementRespostory.existsByEmail(parentManagement.getEmail())) {
			throw new DataIntegrityViolationException("Email address already exists");
		}
	    
	    // Set school and current date
	    parentManagement.setSchoolManagement(schoolManagement);
	    parentManagement.setStudentManagement(studentManagement);
	    parentManagement.setParentRegDateTime(LocalDate.now());
	    parentManagementRespostory.save(parentManagement);
	}

}
