package com.eduConnect.eduConnect.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eduConnect.eduConnect.Config.JwtUtil;
import com.eduConnect.eduConnect.Dto.AdditionalDetails;
import com.eduConnect.eduConnect.Dto.ResponseMessageDto;
import com.eduConnect.eduConnect.Dto.UserLoginDto;
import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.ParentManagement;
import com.eduConnect.eduConnect.Model.SchoolManagement;
import com.eduConnect.eduConnect.Model.StudentManagement;
import com.eduConnect.eduConnect.Model.TeacherManagement;
import com.eduConnect.eduConnect.Model.UsersManagement;
import com.eduConnect.eduConnect.Repositroy.ParentManagementRespostory;
import com.eduConnect.eduConnect.Repositroy.SchoolManagementRespositroy;
import com.eduConnect.eduConnect.Repositroy.StudentManagementRespositroy;
import com.eduConnect.eduConnect.Repositroy.TeacherManagementRepositroy;
import com.eduConnect.eduConnect.Repositroy.UsersManageRepositroy;
import com.eduConnect.eduConnect.RespositroyImpl.UsersManageRespositroyImpl;

@RestController
@RequestMapping("/api/v1/user")
public class UsersManageController {

	@Autowired
	private UsersManageRespositroyImpl usersManageRespositroyImpl;
	
	@Autowired
	private UsersManageRepositroy usersManageRepositroy;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SchoolManagementRespositroy schoolManagementRespositroy;
	
	@Autowired
	private TeacherManagementRepositroy teacherManagementRepositroy;
	
	@Autowired
	private ParentManagementRespostory parentManagementRespostory;
	
	@Autowired
	private StudentManagementRespositroy studentManagementRespositroy;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	    public ResponseEntity<ResponseMessageDto> createUsersManage(@RequestBody UsersManagement usersManagement) {
	        try {
	            UsersManagement savedUser = usersManageRespositroyImpl.saveUserManagement(usersManagement);
	            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "User saved successfully", null, new Date());
	            return ResponseEntity.ok(responseMessage);
	        } catch (DataIntegrityViolationException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Email address already exists", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	        } catch (Exception e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save user", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	        }
	    }

	    @RequestMapping(value ="/update/{userId}", method = RequestMethod.POST)
	    public ResponseEntity<ResponseMessageDto> updateUser(@PathVariable Long userId, @RequestBody UsersManagement updatedUser) {
	        try {
	            UsersManagement updatedUserManagement = usersManageRespositroyImpl.updatedUserManagement(updatedUser, userId);
	            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "User updated successfully", null, new Date());
	            return ResponseEntity.ok(responseMessage);
	        } catch (UserNotFoundException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "User not found", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
	        } catch (IllegalArgumentException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", e.getMessage(), "Email field is required.", new Date());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	        } catch (DataIntegrityViolationException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update user", "Constraint violation: " + e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseMessage);
	        } catch (Exception e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update user", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	        }
	    }
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllUser() {
		List<UsersManagement> usersManagement = usersManageRespositroyImpl.getAllUsersManage();
		if (usersManagement.isEmpty()) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No User found", "User list is empty",
					new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(usersManagement);
		}
	}
	
	@RequestMapping(value = "/details/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUserById(@PathVariable Long userId) {
		try {
			UsersManagement usersManagement = usersManageRespositroyImpl.getUsersManageById(userId);

			  if (usersManagement == null) {
		            throw new RuntimeException("User details not found");
		        }
			  
			return ResponseEntity.ok(usersManagement);
		} catch (RuntimeException e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "User details not found",
					e.getMessage(), new Date());
			return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
		}
	}
	

	
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseMessageDto> deleteUser(@PathVariable Long userId) {
		try {
			usersManageRespositroyImpl.deleteUsersManageById(userId);
			ResponseMessageDto responseMessage = new ResponseMessageDto("success", "User successfully deleted", null,
					new Date());
			return ResponseEntity.ok(responseMessage);
		} catch (Exception e) {
			ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete User",
					e.getMessage(), new Date());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
		}
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public ResponseEntity<?> loginUser(@RequestBody UsersManagement loginRequest) {
//	    String email = loginRequest.getEmail();
//	    String password = loginRequest.getPassword();
//
//	    UsersManagement usersManagement = usersManageRepositroy.findByEmail(email);
//
//	    if (usersManagement == null || !passwordEncoder.matches(password, usersManagement.getPassword())) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//	                .body(new ResponseMessageDto("error", "Invalid email or password", "Unauthorized", new Date()));
//	    }
//
//	    String token = jwtUtil.generateToken(email);
//
//	    UserLoginDto loginResponseDto = new UserLoginDto();
//	    loginResponseDto.setUserId(usersManagement.getUserId());
//	    loginResponseDto.setUserName(usersManagement.getUserName());
//	    loginResponseDto.setEmail(usersManagement.getEmail());
//	    loginResponseDto.setLoginStatus(usersManagement.getLoginStatus());
//	    loginResponseDto.setRole(usersManagement.getRole());	    
//	    loginResponseDto.setToken(token);
//
//	    return ResponseEntity.ok(loginResponseDto);
//	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody UsersManagement loginRequest) {
	    String email = loginRequest.getEmail();
	    String password = loginRequest.getPassword();

	    UsersManagement usersManagement = usersManageRepositroy.findByEmail(email);

	    if (usersManagement == null || !passwordEncoder.matches(password, usersManagement.getPassword())) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(new ResponseMessageDto("error", "Invalid email or password", "Unauthorized", new Date()));
	    }

	    // Check if email exists in the respective tables and get details
	    TeacherManagement teacher = teacherManagementRepositroy.findByEmail(email);
	    StudentManagement student = studentManagementRespositroy.findByEmail(email);
	    SchoolManagement school = schoolManagementRespositroy.findByEmail(email);
	    ParentManagement parent = parentManagementRespostory.findByEmail(email);

	    UserLoginDto loginResponseDto = new UserLoginDto();
	    loginResponseDto.setUserId(usersManagement.getUserId());
	    loginResponseDto.setUserName(usersManagement.getUserName());
	    loginResponseDto.setEmail(usersManagement.getEmail());
	    loginResponseDto.setLoginStatus(usersManagement.getLoginStatus());
	    loginResponseDto.setRole(usersManagement.getRole());

	    // Set additional details based on which table the email was found in
	    if (teacher != null) {
	        loginResponseDto.setAdditionalDetails(new AdditionalDetails("teacher", teacher.getTeacherId(), teacher.getTeacherName()));
	    } else if (student != null) {
	        loginResponseDto.setAdditionalDetails(new AdditionalDetails("student", student.getStudentId(), student.getStudentName()));
	    } else if (school != null) {
	        loginResponseDto.setAdditionalDetails(new AdditionalDetails("school", school.getSchoolId(), school.getSchoolName()));
	    } else if (parent != null) {
	        loginResponseDto.setAdditionalDetails(new AdditionalDetails("parent", parent.getParentId(), parent.getParentName()));
	    }

	    // Generate token
	    String token = jwtUtil.generateToken(email);
	    loginResponseDto.setToken(token);

	    return ResponseEntity.ok(loginResponseDto);
	}

	
	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public long getTotalCameraCount() {
		long totalUser = usersManageRepositroy.count();
		return totalUser;
	}
}
