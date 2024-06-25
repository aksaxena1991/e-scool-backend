package com.eduConnect.eduConnect.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.SchoolManagement;

@Repository
public interface SchoolManagementRespositroy extends JpaRepository<SchoolManagement, Long> {

	boolean existsByEmail(String email);

	SchoolManagement findByEmail(String email);

}
