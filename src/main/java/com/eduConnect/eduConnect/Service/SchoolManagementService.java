package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.SchoolManagement;
import com.eduConnect.eduConnect.Repositroy.SchoolManagementRespositroy;
import com.eduConnect.eduConnect.RespositroyImpl.SchoolManagementRespositroyImpl;

@Service
public class SchoolManagementService implements SchoolManagementRespositroyImpl {

	@Autowired
	private SchoolManagementRespositroy schoolManagementRespositroy;

	@Override
	public SchoolManagement saveSchoolManagement(SchoolManagement schoolManagement) throws Exception {
		if (schoolManagementRespositroy.existsByEmail(schoolManagement.getEmail())) {
			throw new DataIntegrityViolationException("Email address already exists");
		}

		schoolManagement.setSchoolRegDateTime(LocalDate.now());
		return schoolManagementRespositroy.save(schoolManagement);
	}

	@Override
	public SchoolManagement updatedSchoolManagement(SchoolManagement updatedSchool, Long schoolId) throws Exception {
		SchoolManagement existingSchool = schoolManagementRespositroy.findById(schoolId).orElse(null);
		if (existingSchool == null) {
			throw new UserNotFoundException("School not found");
		}

		if (updatedSchool.getEmail() == null || updatedSchool.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email cannot be empty");
		}

		// Update school properties
		existingSchool.setSchoolName(updatedSchool.getSchoolName());
		existingSchool.setEmail(updatedSchool.getEmail());
		existingSchool.setNumber(updatedSchool.getNumber());
		existingSchool.setAddress(updatedSchool.getAddress());

		return schoolManagementRespositroy.save(existingSchool);
	}

	@Override
	public List<SchoolManagement> getAllSchoolManagement() {
		return this.schoolManagementRespositroy.findAll();
	}

	@Override
	public SchoolManagement getSchoolManagementById(Long schoolId) {
		try {
			return schoolManagementRespositroy.findById(schoolId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteSchoolManagementById(Long schoolId) {
		this.schoolManagementRespositroy.deleteById(schoolId);
	}

}
