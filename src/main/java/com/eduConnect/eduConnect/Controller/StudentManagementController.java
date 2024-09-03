package com.eduConnect.eduConnect.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eduConnect.eduConnect.Dto.ResponseMessageDto;
import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.StudentManagement;
import com.eduConnect.eduConnect.Repositroy.StudentManagementRespositroy;
import com.eduConnect.eduConnect.RespositroyImpl.StudentManagementRespositroyImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/student")
public class StudentManagementController {

	@Autowired
	private StudentManagementRespositroyImpl studentManagementRespositroyImpl;

	@Autowired
	private StudentManagementRespositroy studentManagementRespositroy;

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> createStudentManagement(
			@RequestBody StudentManagement studentManagement) {
		try {
			StudentManagement savedStudent = studentManagementRespositroyImpl.saveStudentManagement(studentManagement);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Student details saved successfully",
					null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (DataIntegrityViolationException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Email address already exists",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save Student details",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/update/{studentId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> updateStudentManagement(@PathVariable Long studentId,
			@RequestBody StudentManagement updatedStudent) {
		try {
			StudentManagement updatedStudentManagement = studentManagementRespositroyImpl
					.updatedStudentManagement(updatedStudent, studentId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success",
					"Student details updated successfully", null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (UserNotFoundException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Student details not found",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
		} catch (IllegalArgumentException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", e.getMessage(),
					"Email field is required.", new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (DataIntegrityViolationException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update Student details",
					"Constraint violation: " + e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update school details",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllStudentManagement() {
		List<StudentManagement> studentManagements = studentManagementRespositroyImpl.getAllStudentManagement();
		if (studentManagements.isEmpty()) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No Student found",
					"School list is empty", new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(studentManagements);
		}
	}

	@RequestMapping(value = "/details/{studentId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudentManagementById(@PathVariable Long studentId) {
		try {
			StudentManagement studentManagement = studentManagementRespositroyImpl.getStudentManagementById(studentId);

			if (studentManagement == null) {
				throw new RuntimeException("Student details not found");
			}

			return ResponseEntity.ok(studentManagement);
		} catch (RuntimeException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Student details not found",
					e.getMessage(), new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/delete/{studentId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessageDto> deleteStudentManagement(@PathVariable Long studentId) {
		try {
			studentManagementRespositroyImpl.deleteStudentManagementById(studentId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Student successfully deleted", null,
					new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete Student",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public long getTotalStudentManagementCount() {
		long totalSchool = studentManagementRespositroy.count();
		return totalSchool;
	}

	@RequestMapping(value = "/details/school/{schoolId}", method = RequestMethod.GET)
	public ResponseEntity<List<StudentManagement>> getStudentManagementBySchoolId(@PathVariable Long schoolId) {
		List<StudentManagement> studentManagements = studentManagementRespositroyImpl
				.getStudentManagementBySchoolId(schoolId);
		if (studentManagements.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(studentManagements);
	}
}
