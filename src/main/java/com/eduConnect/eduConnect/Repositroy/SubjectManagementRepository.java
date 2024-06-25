package com.eduConnect.eduConnect.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.SubjectManagement;

@Repository
public interface SubjectManagementRepository extends JpaRepository<SubjectManagement, Long>{

}
