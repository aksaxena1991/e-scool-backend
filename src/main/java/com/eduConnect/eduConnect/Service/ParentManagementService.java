package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.ParentManagement;
import com.eduConnect.eduConnect.Model.SchoolManagement;
import com.eduConnect.eduConnect.Model.StudentManagement;
import com.eduConnect.eduConnect.Repositroy.ParentManagementRespostory;
import com.eduConnect.eduConnect.Repositroy.SchoolManagementRespositroy;
import com.eduConnect.eduConnect.Repositroy.StudentManagementRespositroy;
import com.eduConnect.eduConnect.RespositroyImpl.ParentManagementRespostoryImpl;

@Service
public class ParentManagementService implements ParentManagementRespostoryImpl{

	@Autowired
	private ParentManagementRespostory parentManagementRespostory;

	@Autowired
	private SchoolManagementRespositroy schoolManagementRespositroy;
	
	@Autowired
	private StudentManagementRespositroy studentManagementRespositroy;
	
	
	@Override
	public ParentManagement saveParentManagement(ParentManagement parentManagement) throws Exception {
		 // Validate SchoolId is provided
        if (parentManagement.getSchoolManagement() == null || parentManagement.getSchoolManagement().getSchoolId() == null) {
            throw new UserNotFoundException("School ID is missing");
        }

        // Fetch the School from the database
        SchoolManagement schoolManagement = schoolManagementRespositroy.findById(parentManagement.getSchoolManagement().getSchoolId())
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
		parentManagement.setStudentManagement(studentManagement);
		parentManagement.setSchoolManagement(schoolManagement);
		parentManagement.setParentRegDateTime(LocalDate.now());
		return parentManagementRespostory.save(parentManagement);
	}

	@Override
	public List<ParentManagement> getAllParentManagement() {
		return this.parentManagementRespostory.findAll();
	}

	@Override
	public ParentManagement getParentManagementById(Long parentId) {
		try {
			return parentManagementRespostory.findById(parentId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteParentManagementById(Long parentId) {
		this.parentManagementRespostory.deleteById(parentId);
		
	}

	@Override
	public ParentManagement updatedParentManagement(ParentManagement updatedParent, Long parentId) throws Exception {
		 // Validate SchoolId is provided
        if (updatedParent.getSchoolManagement() == null || updatedParent.getSchoolManagement().getSchoolId() == null) {
            throw new UserNotFoundException("School ID is missing");
        }

        // Fetch the School from the database
        SchoolManagement schoolManagement = schoolManagementRespositroy.findById(updatedParent.getSchoolManagement().getSchoolId())
                                  .orElseThrow(() -> new Exception("School not found"));
        
        
        // Validate StudentId is provided
        if (updatedParent.getStudentManagement() == null || updatedParent.getStudentManagement().getStudentId() == null) {
            throw new UserNotFoundException("Student ID is missing");
        }

        // Fetch the School from the database
        StudentManagement studentManagement = studentManagementRespositroy.findById(updatedParent.getStudentManagement().getStudentId())
                                  .orElseThrow(() -> new Exception("Student not found"));
        
        
		if (parentManagementRespostory.existsByEmail(updatedParent.getEmail())) {
			throw new DataIntegrityViolationException("Email address already exists");
		}

		// Set school and current date
		updatedParent.setStudentManagement(studentManagement);
		updatedParent.setSchoolManagement(schoolManagement);
		updatedParent.setParentName(updatedParent.getParentName());
		updatedParent.setEmail(updatedParent.getEmail());
		updatedParent.setNumber(updatedParent.getNumber());
		updatedParent.setAddress(updatedParent.getAddress());
		return parentManagementRespostory.save(updatedParent);
	}

	@Override
	public List<ParentManagement> getParentManagementBySchoolId(Long schoolId) {
		List<ParentManagement> parentManagements = parentManagementRespostory.findBySchoolManagement_SchoolId(schoolId);
		return parentManagements;
	}

}
