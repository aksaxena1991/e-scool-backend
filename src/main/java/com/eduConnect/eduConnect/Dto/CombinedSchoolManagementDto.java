package com.eduConnect.eduConnect.Dto;

import com.eduConnect.eduConnect.Model.SchoolManagement;
import com.eduConnect.eduConnect.Model.UsersManagement;

public class CombinedSchoolManagementDto {

	private UsersManagement usersManagement;
	private SchoolManagement schoolManagement;

	public UsersManagement getUsersManagement() {
		return usersManagement;
	}

	public void setUsersManagement(UsersManagement usersManagement) {
		this.usersManagement = usersManagement;
	}

	public SchoolManagement getSchoolManagement() {
		return schoolManagement;
	}

	public void setSchoolManagement(SchoolManagement schoolManagement) {
		this.schoolManagement = schoolManagement;
	}

}
