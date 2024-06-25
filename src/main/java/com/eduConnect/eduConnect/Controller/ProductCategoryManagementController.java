package com.eduConnect.eduConnect.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eduConnect.eduConnect.Dto.ResponseMessageDto;
import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.ProductCategoryManagement;
import com.eduConnect.eduConnect.Repositroy.ProductCategoryManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.ProductCategoryManagementRepositoryImpl;

@RestController
@RequestMapping("/api/v1/product-category")
public class ProductCategoryManagementController {

	@Autowired
	private ProductCategoryManagementRepositoryImpl productCategoryManagementRepositoryImpl;
	
	@Autowired
	private ProductCategoryManagementRepository productCategoryManagementRepository;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessageDto> createProductCategory(@RequestBody ProductCategoryManagement productCategoryManagement) {
        try {
        	ProductCategoryManagement savedProductCategory = productCategoryManagementRepositoryImpl.saveProductCategoryManagement(productCategoryManagement);
            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Product-Category saved successfully", null, new Date());
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save Product-Category", e.getMessage(), new Date());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
	
	 @RequestMapping(value ="/update/{productcategoryId}", method = RequestMethod.POST)
	    public ResponseEntity<ResponseMessageDto> updateProductCategory(@PathVariable Long productcategoryId, @RequestBody ProductCategoryManagement updatedProductCategory) {
	        try {
	        	ProductCategoryManagement updatedProductCategoryManagement = productCategoryManagementRepositoryImpl.updatedProductCategoryManagement(updatedProductCategory, productcategoryId);
	            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Product-Category updated successfully", null, new Date());
	            return ResponseEntity.ok(responseMessage);
	        } catch (UserNotFoundException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Product-Category not found", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
	        } catch (Exception e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update Product-Category", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	        }
	    }
	 
	 @RequestMapping(value = "/details", method = RequestMethod.GET)
		public ResponseEntity<Object> getAllProductCategory() {
			List<ProductCategoryManagement> productCategoryManagements = productCategoryManagementRepositoryImpl.getAllProductCategoryManagement();
			if (productCategoryManagements.isEmpty()) {
				ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No Product-Category found", "Product-Category list is empty",
						new Date());
				return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
			} else {
				return ResponseEntity.ok(productCategoryManagements);
			}
		}
	 
	 @RequestMapping(value = "/details/{productcategoryId}", method = RequestMethod.GET)
		public ResponseEntity<Object> getProductcategoryId(@PathVariable Long productcategoryId) {
			try {
				ProductCategoryManagement productCategoryManagement = productCategoryManagementRepositoryImpl.getProductCategoryManagementById(productcategoryId);

				  if (productCategoryManagement == null) {
			            throw new RuntimeException("Product-Category details not found");
			        }
				  
				return ResponseEntity.ok(productCategoryManagement);
			} catch (RuntimeException e) {
				ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Product-Category details not found",
						e.getMessage(), new Date());
				return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
			}
		}
	 
	 @RequestMapping(value = "/delete/{productcategoryId}", method = RequestMethod.GET)
		public ResponseEntity<ResponseMessageDto> deleteProductCategory(@PathVariable Long productcategoryId) {
			try {
				productCategoryManagementRepositoryImpl.deleteProductCategoryManagementById(productcategoryId);
				ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Product-Category successfully deleted", null,
						new Date());
				return ResponseEntity.ok(responseMessage);
			} catch (Exception e) {
				ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete Product-Category",
						e.getMessage(), new Date());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
			}
		}
	 
	 @RequestMapping(value = "/count", method = RequestMethod.GET)
		public long getTotalProductCategoryCount() {
			long totalProductCategory = productCategoryManagementRepository.count();
			return totalProductCategory;
		}
}
