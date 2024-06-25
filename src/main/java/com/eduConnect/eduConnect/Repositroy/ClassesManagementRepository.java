package com.eduConnect.eduConnect.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.ClassesManagement;

@Repository
public interface ClassesManagementRepository extends JpaRepository<ClassesManagement, Long>{

}
