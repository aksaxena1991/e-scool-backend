package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.StudentManagement;

public interface StudentManagementRespositroyImpl {

	StudentManagement saveStudentManagement(StudentManagement schoolManagement) throws Exception;

	List<StudentManagement> getAllStudentManagement();

	StudentManagement getStudentManagementById(Long studentId);

	void deleteStudentManagementById(Long studentId);

	StudentManagement updatedStudentManagement(StudentManagement updatedStudent, Long studentId) throws Exception;

	List<StudentManagement> getStudentManagementBySchoolId(Long schoolId);

}
