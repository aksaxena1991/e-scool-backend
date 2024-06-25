package com.eduConnect.eduConnect.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eduConnect.eduConnect.Dto.ResponseMessageDto;
import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.SchoolManagement;
import com.eduConnect.eduConnect.Repositroy.SchoolManagementRespositroy;
import com.eduConnect.eduConnect.RespositroyImpl.SchoolManagementRespositroyImpl;

@RestController
@RequestMapping("/api/v1/school")
public class SchoolManagementController {

	@Autowired
	private SchoolManagementRespositroyImpl schoolManagementRespositroyImpl;

	@Autowired
	private SchoolManagementRespositroy schoolManagementRespositroy;

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> createSchoolManagement(@RequestBody SchoolManagement schoolManagement) {
		try {
			SchoolManagement savedSchool = schoolManagementRespositroyImpl.saveSchoolManagement(schoolManagement);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "School details saved successfully",
					null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (DataIntegrityViolationException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Email address already exists",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save school details",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/update/{schoolId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> updateSchoolManagement(@PathVariable Long schoolId,
			@RequestBody SchoolManagement updatedSchool) {
		try {
			SchoolManagement updatedSchoolManagement = schoolManagementRespositroyImpl
					.updatedSchoolManagement(updatedSchool, schoolId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success",
					"School details updated successfully", null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (UserNotFoundException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "School details not found",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
		} catch (IllegalArgumentException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", e.getMessage(),
					"Email field is required.", new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (DataIntegrityViolationException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update school details",
					"Constraint violation: " + e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update school details",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllSchoolManagement() {
		List<SchoolManagement> schoolManagements = schoolManagementRespositroyImpl.getAllSchoolManagement();
		if (schoolManagements.isEmpty()) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No School found",
					"School list is empty", new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(schoolManagements);
		}
	}

	@RequestMapping(value = "/details/{schoolId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getSchoolById(@PathVariable Long schoolId) {
		try {
			SchoolManagement schoolManagement = schoolManagementRespositroyImpl.getSchoolManagementById(schoolId);
			
			  if (schoolManagement == null) {
		            throw new RuntimeException("School details not found");
		        }
			  
			return ResponseEntity.ok(schoolManagement);
		} catch (RuntimeException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "School details not found",
					e.getMessage(), new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/delete/{schoolId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessageDto> deleteSchool(@PathVariable Long schoolId) {
		try {
			schoolManagementRespositroyImpl.deleteSchoolManagementById(schoolId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "School successfully deleted", null,
					new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete School",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public long getTotalSchoolCount() {
		long totalSchool = schoolManagementRespositroy.count();
		return totalSchool;
	}
}
