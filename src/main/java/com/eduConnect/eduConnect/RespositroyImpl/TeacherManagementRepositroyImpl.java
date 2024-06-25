package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.TeacherManagement;

public interface TeacherManagementRepositroyImpl {

	TeacherManagement saveTeacherManagement(TeacherManagement teacherManagement) throws Exception;

	List<TeacherManagement> getAllTeacherManagement();

	TeacherManagement getTeacherManagementById(Long teacherId);

	void deleteTeacherManagementById(Long teacherId);

	TeacherManagement updatedTeacherManagement(TeacherManagement updatedTeacher, Long teacherId) throws Exception;
	
	List<TeacherManagement> getTeacherManagementBySchoolId(Long schoolId);

}
