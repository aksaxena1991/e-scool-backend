package com.eduConnect.eduConnect.Repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduConnect.eduConnect.Model.ProductCategoryManagement;

@Repository
public interface ProductCategoryManagementRepository extends JpaRepository<ProductCategoryManagement, Long> {

}
