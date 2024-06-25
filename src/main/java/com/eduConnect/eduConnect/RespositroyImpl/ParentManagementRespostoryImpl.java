package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.ParentManagement;
import com.eduConnect.eduConnect.Model.StudentManagement;

public interface ParentManagementRespostoryImpl {

	ParentManagement saveParentManagement(ParentManagement parentManagement) throws Exception;

	List<ParentManagement> getAllParentManagement();

	ParentManagement getParentManagementById(Long parentId);

	void deleteParentManagementById(Long parentId);

	ParentManagement updatedParentManagement(ParentManagement updatedParent, Long parentId) throws Exception;

	List<ParentManagement> getParentManagementBySchoolId(Long schoolId);

}
