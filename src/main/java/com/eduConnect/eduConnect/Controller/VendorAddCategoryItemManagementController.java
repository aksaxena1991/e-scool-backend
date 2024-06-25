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
import com.eduConnect.eduConnect.Model.VendorAddCategoryItemManagement;
import com.eduConnect.eduConnect.Repositroy.VendorAddCategoryItemManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.VendorAddCategoryItemManagementRepositoryImpl;

@RestController
@RequestMapping("/api/v1/vendor-category-item")
public class VendorAddCategoryItemManagementController {

	@Autowired
	private VendorAddCategoryItemManagementRepositoryImpl vendorAddCategoryItemManagementRepositoryImpl;
	
	@Autowired
	private VendorAddCategoryItemManagementRepository vendorAddCategoryItemManagementRepository;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessageDto> createVendorAddCategoryItem(@RequestBody VendorAddCategoryItemManagement vendorAddCategoryItemManagement) {
        try {
        	VendorAddCategoryItemManagement savedVendorAddCategoryItem = vendorAddCategoryItemManagementRepositoryImpl.saveCityStateManagement(vendorAddCategoryItemManagement);
            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Vendor-category-item saved successfully", null, new Date());
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save Vendor-category-item", e.getMessage(), new Date());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
	
	 @RequestMapping(value ="/update/{vendorcategoryitemId}", method = RequestMethod.POST)
	    public ResponseEntity<ResponseMessageDto> updateVendorAddCategoryItemManagement(@PathVariable Long vendorcategoryitemId, @RequestBody VendorAddCategoryItemManagement updatedVendorAddCategoryItem) {
	        try {
	        	VendorAddCategoryItemManagement updatedVendorAddCategoryItemManagement = vendorAddCategoryItemManagementRepositoryImpl.updatedVendorAddCategoryItemManagement(updatedVendorAddCategoryItem, vendorcategoryitemId);
	            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Vendor-category-item updated successfully", null, new Date());
	            return ResponseEntity.ok(responseMessage);
	        } catch (UserNotFoundException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Vendor-category-item not found", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
	        } catch (Exception e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update Vendor-category-item", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	        }
	    }
	
	 @RequestMapping(value = "/details", method = RequestMethod.GET)
		public ResponseEntity<Object> getAllVendorAddCategoryItemManagement() {
			List<VendorAddCategoryItemManagement> vendorAddCategoryItemManagements = vendorAddCategoryItemManagementRepositoryImpl.getAllVendorAddCategoryItemManagement();
			if (vendorAddCategoryItemManagements.isEmpty()) {
				ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No Vendor-category-item found", "Vendor-category-item list is empty",
						new Date());
				return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
			} else {
				return ResponseEntity.ok(vendorAddCategoryItemManagements);
			}
		}
	 
	 @RequestMapping(value = "/details/{vendorcategoryitemId}", method = RequestMethod.GET)
		public ResponseEntity<Object> getVendorcategoryitemId(@PathVariable Long vendorcategoryitemId) {
			try {
				VendorAddCategoryItemManagement vendorAddCategoryItemManagement = vendorAddCategoryItemManagementRepositoryImpl.getVendorAddCategoryItemManagementById(vendorcategoryitemId);

				  if (vendorAddCategoryItemManagement == null) {
			            throw new RuntimeException("Vendor-category-item details not found");
			        }
				  
				return ResponseEntity.ok(vendorAddCategoryItemManagement);
			} catch (RuntimeException e) {
				ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Vendor-category-item details not found",
						e.getMessage(), new Date());
				return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
			}
		}
	 
	 @RequestMapping(value = "/delete/{vendorcategoryitemId}", method = RequestMethod.GET)
		public ResponseEntity<ResponseMessageDto> deleteVendorcategoryitem(@PathVariable Long vendorcategoryitemId) {
			try {
				vendorAddCategoryItemManagementRepositoryImpl.deleteVendorAddCategoryItemManagementById(vendorcategoryitemId);
				ResponseMessageDto responseMessage = new ResponseMessageDto("success", "Vendor-category-item successfully deleted", null,
						new Date());
				return ResponseEntity.ok(responseMessage);
			} catch (Exception e) {
				ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete Vendor-category-item",
						e.getMessage(), new Date());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
			}
		}
	 
	 @RequestMapping(value = "/count", method = RequestMethod.GET)
		public long getTotalVendorAddCategoryItemCount() {
			long totalVendorAddCategoryItem = vendorAddCategoryItemManagementRepository.count();
			return totalVendorAddCategoryItem;
		}
	
}
