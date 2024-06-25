package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.ProductCategoryManagement;
import com.eduConnect.eduConnect.Repositroy.ProductCategoryManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.ProductCategoryManagementRepositoryImpl;

@Service
public class ProductCategoryManagementService implements ProductCategoryManagementRepositoryImpl {

	@Autowired
	private ProductCategoryManagementRepository productCategoryManagementRepository;

	@Override
	public ProductCategoryManagement saveProductCategoryManagement(ProductCategoryManagement productCategoryManagement)
			throws Exception {
		productCategoryManagement.setProductcategoryRegDateTime(LocalDate.now());
		return productCategoryManagementRepository.save(productCategoryManagement);
	}

	@Override
	public List<ProductCategoryManagement> getAllProductCategoryManagement() {
		return this.productCategoryManagementRepository.findAll();
	}

	@Override
	public ProductCategoryManagement getProductCategoryManagementById(Long productcategoryId) {
		try {
			return productCategoryManagementRepository.findById(productcategoryId).orElseThrow();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteProductCategoryManagementById(Long productcategoryId) {
		this.productCategoryManagementRepository.deleteById(productcategoryId);

	}

	@Override
	public ProductCategoryManagement updatedProductCategoryManagement(ProductCategoryManagement updatedProductCategory,
			Long productcategoryId) throws Exception {
		ProductCategoryManagement existingCategoryManagement = productCategoryManagementRepository
				.findById(productcategoryId).orElseThrow();
		if (existingCategoryManagement == null) {
			throw new UserNotFoundException("ProductCategory not found");
		}
		existingCategoryManagement.setPeoplecategoryName(updatedProductCategory.getPeoplecategoryName());
		existingCategoryManagement.setProductcategoryName(updatedProductCategory.getProductcategoryName());

		return productCategoryManagementRepository.save(existingCategoryManagement);

	}

}
