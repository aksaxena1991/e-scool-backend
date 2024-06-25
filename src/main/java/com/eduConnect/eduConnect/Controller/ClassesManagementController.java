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
import com.eduConnect.eduConnect.Model.ClassesManagement;
import com.eduConnect.eduConnect.Repositroy.ClassesManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.ClassesManagementRepositoryImpl;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassesManagementController {

	@Autowired
	private ClassesManagementRepositoryImpl classesManagementRepositoryImpl;

	@Autowired
	private ClassesManagementRepository classesManagementRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> createClassesManagement(
			@RequestBody ClassesManagement classesManagement) {
		try {
			ClassesManagement savedClasses = classesManagementRepositoryImpl.saveClassesManagement(classesManagement);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Classes saved successfully", null,
					new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save Classes",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/update/{classId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> updateClassesManagement(@PathVariable Long classId,
			@RequestBody ClassesManagement updatedClasses) {
		try {
			ClassesManagement updatedClassesManagement = classesManagementRepositoryImpl
					.updatedClassesManagement(updatedClasses, classId);
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
	public ResponseEntity<Object> getAllClassesManagement() {
		List<ClassesManagement> classesManagements = classesManagementRepositoryImpl.getAllClassesManagement();
		if (classesManagements.isEmpty()) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No Classes found",
					"Classes list is empty", new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(classesManagements);
		}
	}

	@RequestMapping(value = "/details/{classId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getClassesId(@PathVariable Long classId) {
		try {
			ClassesManagement classesManagement = classesManagementRepositoryImpl.getClassesManagementById(classId);

			if (classesManagement == null) {
				throw new RuntimeException("Classes details not found");
			}

			return ResponseEntity.ok(classesManagement);
		} catch (RuntimeException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Classes details not found",
					e.getMessage(), new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/delete/{classId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessageDto> deleteClassesManagement(@PathVariable Long classId) {
		try {
			classesManagementRepositoryImpl.deleteClassesManagementById(classId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Classes successfully deleted", null,
					new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete classes",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public long getTotalClassesCount() {
		long totalClasses = classesManagementRepository.count();
		return totalClasses;
	}

}
