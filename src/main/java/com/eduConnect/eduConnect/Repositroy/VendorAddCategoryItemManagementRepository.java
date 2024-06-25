package com.eduConnect.eduConnect.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.VendorAddCategoryItemManagement;

@Repository
public interface VendorAddCategoryItemManagementRepository extends JpaRepository<VendorAddCategoryItemManagement, Long> {

}
