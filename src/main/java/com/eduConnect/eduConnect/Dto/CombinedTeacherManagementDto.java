package com.eduConnect.eduConnect.Dto;

import com.eduConnect.eduConnect.Model.TeacherManagement;
import com.eduConnect.eduConnect.Model.UsersManagement;

public class CombinedTeacherManagementDto {

	private UsersManagement usersManagement;
	private TeacherManagement teacherManagement;

	public UsersManagement getUsersManagement() {
		return usersManagement;
	}

	public void setUsersManagement(UsersManagement usersManagement) {
		this.usersManagement = usersManagement;
	}

	public TeacherManagement getTeacherManagement() {
		return teacherManagement;
	}

	public void setTeacherManagement(TeacherManagement teacherManagement) {
		this.teacherManagement = teacherManagement;
	}

}
