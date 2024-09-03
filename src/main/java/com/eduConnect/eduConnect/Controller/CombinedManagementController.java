package com.eduConnect.eduConnect.Controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eduConnect.eduConnect.Dto.CombinedParentManagementDto;
import com.eduConnect.eduConnect.Dto.CombinedSchoolManagementDto;
import com.eduConnect.eduConnect.Dto.CombinedStudentManagementDto;
import com.eduConnect.eduConnect.Dto.CombinedTeacherManagementDto;
import com.eduConnect.eduConnect.Dto.ResponseMessageDto;
import com.eduConnect.eduConnect.Service.CombinedManagementService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CombinedManagementController {

	 @Autowired
	    private CombinedManagementService combinedManagementService;

	    @RequestMapping(value ="/school-reg", method = RequestMethod.POST)
	    public ResponseEntity<ResponseMessageDto> saveCombinedSchoolManagement(@RequestBody CombinedSchoolManagementDto combinedSchoolManagementDto) {
	        try {
	        	combinedManagementService.saveCombinedSchoolManagement(combinedSchoolManagementDto);
	            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "School data saved successfully", null, new Date());
	            return ResponseEntity.ok(responseMessage);
	        } catch (DataIntegrityViolationException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", e.getMessage(), e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	        } catch (Exception e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save school data", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	        }
	    }

	    @RequestMapping(value = "/teacher-reg", method = RequestMethod.POST)
	    public ResponseEntity<ResponseMessageDto> saveCombinedTeacherManagement(@RequestBody CombinedTeacherManagementDto combinedTeacherManagementDto) {
	        try {
	            // Validate SchoolId is provided
	            if (combinedTeacherManagementDto.getTeacherManagement() == null 
	                    || combinedTeacherManagementDto.getTeacherManagement().getSchoolManagement() == null 
	                    || combinedTeacherManagementDto.getTeacherManagement().getSchoolManagement().getSchoolId() == null) {
	                ResponseMessageDto responseMessage = new ResponseMessageDto("error", "School ID is missing", "School ID is missing", new Date());
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	            }

	            combinedManagementService.saveCombinedTeacherManagement(combinedTeacherManagementDto);
	            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Teacher data saved successfully", null, new Date());
	            return ResponseEntity.ok(responseMessage);
	        } catch (DataIntegrityViolationException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", e.getMessage(), e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	        } catch (Exception e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save teacher data", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	        }
	    }

	    
	    @RequestMapping(value ="/student-reg", method = RequestMethod.POST)
	    public ResponseEntity<ResponseMessageDto> saveCombinedStudentManagement(@RequestBody CombinedStudentManagementDto combinedStudentManagementDto) {
	        try {
	        	// Validate SchoolId is provided
	            if (combinedStudentManagementDto.getStudentManagement() == null 
	                    || combinedStudentManagementDto.getStudentManagement().getSchoolManagement() == null 
	                    || combinedStudentManagementDto.getStudentManagement().getSchoolManagement().getSchoolId() == null) {
	                ResponseMessageDto responseMessage = new ResponseMessageDto("error", "School ID is missing", "School ID is missing", new Date());
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	            }

	        	combinedManagementService.saveCombinedStudentManagement(combinedStudentManagementDto);
	            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Student data saved successfully", null, new Date());
	            return ResponseEntity.ok(responseMessage);
	        } catch (DataIntegrityViolationException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", e.getMessage(), e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	        } catch (Exception e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save student data", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	        }
	    }
	    
	    @RequestMapping(value ="/parent-reg", method = RequestMethod.POST)
	    public ResponseEntity<ResponseMessageDto> saveCombinedParentManagement(@RequestBody CombinedParentManagementDto combinedParentManagementDto) {
	        try {
	        	// Validate SchoolId is provided
	            if (combinedParentManagementDto.getParentManagement() == null 
	                    || combinedParentManagementDto.getParentManagement().getSchoolManagement() == null 
	                    || combinedParentManagementDto.getParentManagement().getSchoolManagement().getSchoolId() == null) {
	                ResponseMessageDto responseMessage = new ResponseMessageDto("error", "School ID is missing", "School ID is missing", new Date());
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	            }
	            
	            if (combinedParentManagementDto.getParentManagement() == null 
	                    || combinedParentManagementDto.getParentManagement().getStudentManagement() == null 
	                    || combinedParentManagementDto.getParentManagement().getStudentManagement().getStudentId() == null) {
	                ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Student ID is missing", "Student ID is missing", new Date());
	                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	            }

	        	combinedManagementService.saveCombinedParentManagement(combinedParentManagementDto);
	            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Parent data saved successfully", null, new Date());
	            return ResponseEntity.ok(responseMessage);
	        } catch (DataIntegrityViolationException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", e.getMessage(), e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	        } catch (Exception e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save student data", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	        }
	    }
}
