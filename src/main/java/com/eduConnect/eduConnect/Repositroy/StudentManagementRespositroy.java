package com.eduConnect.eduConnect.Repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.StudentManagement;

@Repository
public interface StudentManagementRespositroy extends JpaRepository<StudentManagement, Long>{
	long count();

	boolean existsByEmail(String email);

	List<StudentManagement> findBySchoolManagement_SchoolId(Long schoolId);

	StudentManagement findByEmail(String email);
	
}
