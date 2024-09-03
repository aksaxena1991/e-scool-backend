package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.AttendanceManagement;

public interface AttendanceManagementRepositoryImpl {

	AttendanceManagement saveAttendanceManagement(AttendanceManagement attendanceManagement) throws Exception;

	List<AttendanceManagement> getAllAttendanceManagement();

	AttendanceManagement getAttendanceManagementById(Long attendanceId);

	void deleteAttendanceManagementById(Long attendanceId);

	AttendanceManagement updatedAttendanceManagement(AttendanceManagement updatedAttendance, Long attendanceId) throws Exception;

}
