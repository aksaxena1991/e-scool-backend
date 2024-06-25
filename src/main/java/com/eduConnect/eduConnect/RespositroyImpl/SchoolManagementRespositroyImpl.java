package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.SchoolManagement;

public interface SchoolManagementRespositroyImpl{

	SchoolManagement saveSchoolManagement(SchoolManagement schoolManagement) throws Exception;

	List<SchoolManagement> getAllSchoolManagement();

	SchoolManagement getSchoolManagementById(Long schoolId);

	void deleteSchoolManagementById(Long schoolId);

	SchoolManagement updatedSchoolManagement(SchoolManagement updatedSchool, Long schoolId) throws Exception;
}
