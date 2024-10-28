package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.UsersManagement;


public interface UsersManageRespositroyImpl {

	UsersManagement saveUserManagement(UsersManagement usersManage) throws Exception;

	List<UsersManagement> getAllUsersManage();

	UsersManagement getUsersManageById(Long userId);

	boolean deleteUsersManageById(Long userId);

	UsersManagement updatedUserManagement(UsersManagement updatedUser, Long userId) throws Exception;
}
