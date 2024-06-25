package com.eduConnect.eduConnect.Dto;

import com.eduConnect.eduConnect.Model.ParentManagement;
import com.eduConnect.eduConnect.Model.UsersManagement;

public class CombinedParentManagementDto {

	private UsersManagement usersManagement;
	private ParentManagement parentManagement;

	public UsersManagement getUsersManagement() {
		return usersManagement;
	}

	public void setUsersManagement(UsersManagement usersManagement) {
		this.usersManagement = usersManagement;
	}

	public ParentManagement getParentManagement() {
		return parentManagement;
	}

	public void setParentManagement(ParentManagement parentManagement) {
		this.parentManagement = parentManagement;
	}
}
