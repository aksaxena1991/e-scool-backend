package com.eduConnect.eduConnect.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.eduConnect.eduConnect.Model.AttendanceManagement;
import com.eduConnect.eduConnect.RespositroyImpl.AttendanceManagementRepositoryImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceManagementController {

	@Autowired
	private AttendanceManagementRepositoryImpl attendanceManagementRepositoryImpl;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> createAttendance(@RequestBody AttendanceManagement attendanceManagement) {
		try {
			AttendanceManagement savedAttendance = attendanceManagementRepositoryImpl
					.saveAttendanceManagement(attendanceManagement);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Attendance saved successfully",
					null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save Attendance",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/update/{attendanceId}", method = RequestMethod.POST)
	public ResponseEntity<ResponseMessageDto> updateAttendance(@PathVariable Long attendanceId,
			@RequestBody AttendanceManagement updatedAttendance) {
		try {
			AttendanceManagement updatedAttendances = attendanceManagementRepositoryImpl
					.updatedAttendanceManagement(updatedAttendance, attendanceId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Attendance updated successfully",
					null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (UserNotFoundException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Attendance not found", e.getMessage(),
					new Date());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update Attendance",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}

	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllAttendance() {
		List<AttendanceManagement> attendanceManagements = attendanceManagementRepositoryImpl
				.getAllAttendanceManagement();
		if (attendanceManagements.isEmpty()) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No attendance found",
					"Attendance list is empty", new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(attendanceManagements);
		}
	}

	@RequestMapping(value = "/details/{attendanceId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAttendanceId(@PathVariable Long attendanceId) {
		try {
			AttendanceManagement attendanceManagement = attendanceManagementRepositoryImpl
					.getAttendanceManagementById(attendanceId);

			if (attendanceManagement == null) {
				throw new RuntimeException("Attendance details not found");
			}

			return ResponseEntity.ok(attendanceManagement);
		} catch (RuntimeException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Attendance details not found",
					e.getMessage(), new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/delete/{attendanceId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessageDto> deleteAttendance(@PathVariable Long attendanceId) {
		try {
			attendanceManagementRepositoryImpl.deleteAttendanceManagementById(attendanceId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Attendance successfully deleted",
					null, new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete attendance",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
}
