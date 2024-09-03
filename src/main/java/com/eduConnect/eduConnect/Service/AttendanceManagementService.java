package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.AttendanceManagement;
import com.eduConnect.eduConnect.Repositroy.AttendanceManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.AttendanceManagementRepositoryImpl;


@Service
public class AttendanceManagementService implements AttendanceManagementRepositoryImpl {

	@Autowired
	private AttendanceManagementRepository attendanceManagementRepository;
	
	@Override
	public AttendanceManagement saveAttendanceManagement(AttendanceManagement attendanceManagement) throws Exception {
		attendanceManagement.setAttendanceDateTime(LocalDate.now());
		return attendanceManagementRepository.save(attendanceManagement);
	}

	@Override
	public List<AttendanceManagement> getAllAttendanceManagement() {
		return this.attendanceManagementRepository.findAll();
	}

	@Override
	public AttendanceManagement getAttendanceManagementById(Long attendanceId) {
		try {
			return attendanceManagementRepository.findById(attendanceId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteAttendanceManagementById(Long attendanceId) {
		this.attendanceManagementRepository.deleteById(attendanceId);
	}

	@Override
	public AttendanceManagement updatedAttendanceManagement(AttendanceManagement updatedAttendance, Long attendanceId)
			throws Exception {
		AttendanceManagement existingAttendance = attendanceManagementRepository.findById(attendanceId).orElse(null);
        if (existingAttendance == null) {
            throw new UserNotFoundException("Attendance not found");
        }

        existingAttendance.setAttendance(updatedAttendance.getAttendance());
        existingAttendance.setClassName(updatedAttendance.getClassName());
        existingAttendance.setSubject(updatedAttendance.getSubject());
	return attendanceManagementRepository.save(existingAttendance);
	}

}
