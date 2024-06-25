package com.eduConnect.eduConnect.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.UsersManagement;


@Repository
public interface UsersManageRepositroy extends JpaRepository<UsersManagement, Long>{
	
	long count();
//	UsersManagement findByNumber(String number);
	UsersManagement findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	UsersManagement findByEmailAndOtp(String email, String otp);
}
