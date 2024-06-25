package com.eduConnect.eduConnect.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.CityStateManagement;

@Repository
public interface CityStateManagementRepository extends JpaRepository<CityStateManagement, Long>{

}
