package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Config.JwtUtil;
import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.UsersManagement;
import com.eduConnect.eduConnect.Repositroy.UsersManageRepositroy;
import com.eduConnect.eduConnect.RespositroyImpl.UsersManageRespositroyImpl;

@Service
public class 	UsersManageService implements UsersManageRespositroyImpl {

	@Autowired
	private UsersManageRepositroy usersManageRepositroy;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	 @Override
	    public UsersManagement saveUserManagement(UsersManagement usersManagement) throws Exception {
	        if (usersManageRepositroy.existsByEmail(usersManagement.getEmail())) {
	            throw new DataIntegrityViolationException("Email address already exists");
	        }

	        usersManagement.setUsersAddDateTime(LocalDate.now());
	        usersManagement.setPassword(passwordEncoder.encode(usersManagement.getPassword())); // Encrypt the password

	        return usersManageRepositroy.save(usersManagement);
	    }

	    @Override
	    public UsersManagement updatedUserManagement(UsersManagement updatedUser, Long userId) throws Exception {
	        UsersManagement existingUser = usersManageRepositroy.findById(userId).orElse(null);
	        if (existingUser == null) {
	            throw new UserNotFoundException("User not found");
	        }

	        if (updatedUser.getEmail() == null || updatedUser.getEmail().isEmpty()) {
	            throw new IllegalArgumentException("Email cannot be empty");
	        }

	        // Update user properties
	        existingUser.setUserName(updatedUser.getUserName());
	        existingUser.setEmail(updatedUser.getEmail());
	        existingUser.setNumber(updatedUser.getNumber());
	        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword())); // Encrypt the password

	        // Only update loginStatus if explicitly set
	        if (updatedUser.getLoginStatus() != null) {
	            existingUser.setLoginStatus(updatedUser.getLoginStatus());
	        }

	        return usersManageRepositroy.save(existingUser);
	    }
	
	@Override
	public List<UsersManagement> getAllUsersManage() {
		return this.usersManageRepositroy.findAll();
	}

	@Override
	public UsersManagement getUsersManageById(Long userId) {
		try {
			return usersManageRepositroy.findById(userId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean deleteUsersManageById(Long userId) {
		this.usersManageRepositroy.deleteById(userId);
		return false;
	}

}
