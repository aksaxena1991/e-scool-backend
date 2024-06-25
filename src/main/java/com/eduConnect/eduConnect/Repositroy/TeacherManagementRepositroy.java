package com.eduConnect.eduConnect.Repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.TeacherManagement;

@Repository
public interface TeacherManagementRepositroy extends JpaRepository<TeacherManagement, Long> {
	boolean existsByEmail(String email);

	List<TeacherManagement> findBySchoolManagement_SchoolId(Long schoolId);

	TeacherManagement findByEmail(String email);
}
