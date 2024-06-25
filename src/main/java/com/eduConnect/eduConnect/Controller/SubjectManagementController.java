package com.eduConnect.eduConnect.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eduConnect.eduConnect.Dto.ResponseMessageDto;
import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.SubjectManagement;
import com.eduConnect.eduConnect.Repositroy.SubjectManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.SubjectManagementRepositoryImpl;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectManagementController {
	
	@Autowired
	private SubjectManagementRepositoryImpl subjectManagementRepositoryImpl;
	
	@Autowired
	private SubjectManagementRepository subjectManagementRepository;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> createSubjectManagement(
			@RequestBody SubjectManagement subjectManagement) {
		try {
			SubjectManagement savedSubjectManagement = subjectManagementRepositoryImpl.saveSubjectManagement(subjectManagement);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Subject saved successfully", null,
					new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save subject",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
	@RequestMapping(value = "/update/{subjectId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> updateSubjectManagement(@PathVariable Long subjectId,
			@RequestBody SubjectManagement updatedSubject) {
		try {
			SubjectManagement updatedSubjectManagement = subjectManagementRepositoryImpl
					.updatedSubjectManagement(updatedSubject, subjectId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Classes updated successfully", null,
					new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (UserNotFoundException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Classes not found", e.getMessage(),
					new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update Classes",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllSubjectManagement() {
		List<SubjectManagement> subjectManagements = subjectManagementRepositoryImpl.getAllSubjectManagement();
		if (subjectManagements.isEmpty()) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No Subject found",
					"Subject list is empty", new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(subjectManagements);
		}
	}
	
	@RequestMapping(value = "/details/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getSubjectId(@PathVariable Long subjectId) {
		try {
			SubjectManagement subjectManagement = subjectManagementRepositoryImpl.getSubjectManagementById(subjectId);

			if (subjectManagement == null) {
				throw new RuntimeException("Subject details not found");
			}

			return ResponseEntity.ok(subjectManagement);
		} catch (RuntimeException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Subject details not found",
					e.getMessage(), new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/delete/{subjectId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessageDto> deleteSubjectManagement(@PathVariable Long subjectId) {
		try {
			subjectManagementRepositoryImpl.deleteSubjectManagementById(subjectId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Subject successfully deleted", null,
					new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete subject",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public long getTotalSubjectCount() {
		long totalSubject = subjectManagementRepository.count();
		return totalSubject;
	}

	
}
