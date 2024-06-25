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
import com.eduConnect.eduConnect.Model.ParentManagement;
import com.eduConnect.eduConnect.Model.StudentManagement;
import com.eduConnect.eduConnect.Repositroy.ParentManagementRespostory;
import com.eduConnect.eduConnect.RespositroyImpl.ParentManagementRespostoryImpl;

@RestController
@RequestMapping("/api/v1/parent")
public class ParentManagementController {

	@Autowired
	private ParentManagementRespostoryImpl parentManagementRespostoryImpl;

	@Autowired
	private ParentManagementRespostory parentManagementRespostory;

	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> createParentManagement(@RequestBody ParentManagement parentManagement) {
		try {
			ParentManagement savedParentManagement = parentManagementRespostoryImpl
					.saveParentManagement(parentManagement);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Parent details saved successfully",
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

	@RequestMapping(value = "/update/{parentId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> updateParentManagement(@PathVariable Long parentId,
			@RequestBody ParentManagement updatedParent) {
		try {
			ParentManagement updatedParentManagement = parentManagementRespostoryImpl
					.updatedParentManagement(updatedParent, parentId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success",
					"parent details updated successfully", null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (UserNotFoundException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "parent details not found",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
		} catch (IllegalArgumentException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", e.getMessage(),
					"Email field is required.", new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (DataIntegrityViolationException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update parent details",
					"Constraint violation: " + e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update parent details",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllParentManagement() {
		List<ParentManagement> parentManagements = parentManagementRespostoryImpl.getAllParentManagement();
		if (parentManagements.isEmpty()) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No ParentManagement found",
					"ParentManagement list is empty", new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(parentManagements);
		}
	}

	@RequestMapping(value = "/details/{parentId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getParentManagementById(@PathVariable Long parentId) {
		try {
			ParentManagement parentManagement = parentManagementRespostoryImpl.getParentManagementById(parentId);

			if (parentManagement == null) {
				throw new RuntimeException("Parent details not found");
			}

			return ResponseEntity.ok(parentManagement);
		} catch (RuntimeException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Parent details not found",
					e.getMessage(), new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/delete/{parentId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessageDto> deleteParentManagement(@PathVariable Long parentId) {
		try {
			parentManagementRespostoryImpl.deleteParentManagementById(parentId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Parent successfully deleted", null,
					new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete Parent",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public long getTotalParentCount() {
		long totalParent = parentManagementRespostory.count();
		return totalParent;
	}
	
	@RequestMapping(value = "/details/school/{schoolId}", method = RequestMethod.GET)
	public ResponseEntity<List<ParentManagement>> getParentManagementBySchoolId(@PathVariable Long schoolId) {
		List<ParentManagement> parentManagements = parentManagementRespostoryImpl
				.getParentManagementBySchoolId(schoolId);
		if (parentManagements.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(parentManagements);
	}

}
