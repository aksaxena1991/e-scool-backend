package com.eduConnect.eduConnect.Repositroy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.ParentManagement;

@Repository
public interface ParentManagementRespostory extends JpaRepository<ParentManagement, Long> {

	long count();

	boolean existsByEmail(String email);

	ParentManagement findByEmail(String email);


    List<ParentManagement> findBySchoolManagement_SchoolId(Long schoolId);
}
