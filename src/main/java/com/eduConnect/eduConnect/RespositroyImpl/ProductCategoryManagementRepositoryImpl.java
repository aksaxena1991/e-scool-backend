package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.ProductCategoryManagement;

public interface ProductCategoryManagementRepositoryImpl {

	ProductCategoryManagement saveProductCategoryManagement(ProductCategoryManagement productCategoryManagement) throws Exception;

	List<ProductCategoryManagement> getAllProductCategoryManagement();

	ProductCategoryManagement getProductCategoryManagementById(Long productcategoryId);

	void deleteProductCategoryManagementById(Long productcategoryId);

	ProductCategoryManagement updatedProductCategoryManagement(ProductCategoryManagement updatedProductCategory, Long productcategoryId) throws Exception;

}
