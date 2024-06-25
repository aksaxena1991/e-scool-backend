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
import com.eduConnect.eduConnect.Model.TeacherManagement;
import com.eduConnect.eduConnect.Repositroy.TeacherManagementRepositroy;
import com.eduConnect.eduConnect.RespositroyImpl.TeacherManagementRepositroyImpl;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherManagementController {

	@Autowired
	private TeacherManagementRepositroyImpl teacherManagementRepositroyImpl;

	@Autowired
	private TeacherManagementRepositroy teacherManagementRepositroy;

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> createTeacherManagement(
			@RequestBody TeacherManagement teacherManagement) {
		try {
			TeacherManagement savedTeacher = teacherManagementRepositroyImpl.saveTeacherManagement(teacherManagement);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Teacher details saved successfully",
					null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (DataIntegrityViolationException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Email address already exists",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save teacher details",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/update/{teacherId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> updateTeacherManagement(@PathVariable Long teacherId,
			@RequestBody TeacherManagement updatedTeacher) {
		try {
			TeacherManagement updatedTeacherManagement = teacherManagementRepositroyImpl
					.updatedTeacherManagement(updatedTeacher, teacherId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success",
					"Teacher details updated successfully", null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (UserNotFoundException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Teacher details not found",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
		} catch (IllegalArgumentException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", e.getMessage(),
					"Email field is required.", new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (DataIntegrityViolationException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update teacher details",
					"Constraint violation: " + e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update teacher details",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllTeacherManagement() {
		List<TeacherManagement> teacherManagements = teacherManagementRepositroyImpl.getAllTeacherManagement();
		if (teacherManagements.isEmpty()) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No teacher found",
					"School list is empty", new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(teacherManagements);
		}
	}

	@RequestMapping(value = "/details/{teacherId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getTeacherManagementById(@PathVariable Long teacherId) {
	    try {
	        TeacherManagement teacherManagement = teacherManagementRepositroyImpl.getTeacherManagementById(teacherId);
	        
	        if (teacherManagement == null) {
	            throw new RuntimeException("Teacher details not found");
	        }
	        
	        return ResponseEntity.ok(teacherManagement);
	    } catch (RuntimeException e) {
	        ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Teacher details not found",
	                e.getMessage(), new Date());
	        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
	    }
	}


	@RequestMapping(value = "/delete/{teacherId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessageDto> deleteTeacherManagement(@PathVariable Long teacherId) {
		try {
			teacherManagementRepositroyImpl.deleteTeacherManagementById(teacherId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success",
					"Teacher details successfully deleted", null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete teacher details",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public long getTotalteacherIdCount() {
		long totalTeacher = teacherManagementRepositroy.count();
		return totalTeacher;
	}
	
	@RequestMapping(value = "/details/school/{schoolId}", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherManagement>> getTeacherManagementBySchoolId(@PathVariable Long schoolId) {
        List<TeacherManagement> teacherManagements = teacherManagementRepositroyImpl.getTeacherManagementBySchoolId(schoolId);
        if (teacherManagements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacherManagements);
    }
}
