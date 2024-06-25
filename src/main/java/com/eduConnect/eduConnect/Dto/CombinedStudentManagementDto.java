package com.eduConnect.eduConnect.Dto;

import com.eduConnect.eduConnect.Model.StudentManagement;
import com.eduConnect.eduConnect.Model.UsersManagement;

public class CombinedStudentManagementDto {

	private UsersManagement usersManagement;
	private StudentManagement studentManagement;

	public UsersManagement getUsersManagement() {
		return usersManagement;
	}

	public void setUsersManagement(UsersManagement usersManagement) {
		this.usersManagement = usersManagement;
	}

	public StudentManagement getStudentManagement() {
		return studentManagement;
	}

	public void setStudentManagement(StudentManagement studentManagement) {
		this.studentManagement = studentManagement;
	}

}
