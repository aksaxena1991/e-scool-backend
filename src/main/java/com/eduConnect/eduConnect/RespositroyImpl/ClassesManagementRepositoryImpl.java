package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.ClassesManagement;

public interface ClassesManagementRepositoryImpl {
 
	ClassesManagement saveClassesManagement(ClassesManagement classesManagement) throws Exception;

	List<ClassesManagement> getAllClassesManagement();

	ClassesManagement getClassesManagementById(Long classId);

	void deleteClassesManagementById(Long classId);

	ClassesManagement updatedClassesManagement(ClassesManagement updatedClasses, Long classId) throws Exception;

}
