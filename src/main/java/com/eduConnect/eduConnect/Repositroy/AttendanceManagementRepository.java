package com.eduConnect.eduConnect.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.AttendanceManagement;

@Repository
public interface AttendanceManagementRepository extends JpaRepository<AttendanceManagement, Long>{

}
