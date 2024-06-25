package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.SchoolManagement;
import com.eduConnect.eduConnect.Model.UsersManagement;
import com.eduConnect.eduConnect.Model.VendorAddCategoryItemManagement;
import com.eduConnect.eduConnect.Repositroy.UsersManageRepositroy;
import com.eduConnect.eduConnect.Repositroy.VendorAddCategoryItemManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.VendorAddCategoryItemManagementRepositoryImpl;

@Service
public class VendorAddCategoryItemManagementService implements VendorAddCategoryItemManagementRepositoryImpl{

	@Autowired
	private VendorAddCategoryItemManagementRepository vendorAddCategoryItemManagementRepository;
	
	@Autowired
	private UsersManageRepositroy usersManageRepositroy;
	
	@Override
	public VendorAddCategoryItemManagement saveCityStateManagement(
			VendorAddCategoryItemManagement vendorAddCategoryItemManagement) throws Exception {
		
		// Validate SchoolId is provided
        if (vendorAddCategoryItemManagement.getUsersManagement() == null || vendorAddCategoryItemManagement.getUsersManagement().getUserId() == null) {
            throw new UserNotFoundException("Vendor ID is missing");
        }

        // Fetch the School from the database
        UsersManagement usersManagement = usersManageRepositroy.findById(vendorAddCategoryItemManagement.getUsersManagement().getUserId())
                                  .orElseThrow(() -> new Exception("Vendor not found"));
        
     // Set User and current date
        vendorAddCategoryItemManagement.setUsersManagement(usersManagement);
		vendorAddCategoryItemManagement.setVendorcategoryitemRegDateTime(LocalDate.now());
		return vendorAddCategoryItemManagementRepository.save(vendorAddCategoryItemManagement);
	}

	@Override
	public List<VendorAddCategoryItemManagement> getAllVendorAddCategoryItemManagement() {
		return this.vendorAddCategoryItemManagementRepository.findAll();
	}

	@Override
	public VendorAddCategoryItemManagement getVendorAddCategoryItemManagementById(Long vendorcategoryitemId) {
		try {
			return vendorAddCategoryItemManagementRepository.findById(vendorcategoryitemId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteVendorAddCategoryItemManagementById(Long vendorcategoryitemId) {
		this.vendorAddCategoryItemManagementRepository.deleteById(vendorcategoryitemId);
	}

	@Override
	public VendorAddCategoryItemManagement updatedVendorAddCategoryItemManagement(
			VendorAddCategoryItemManagement updatedVendorAddCategoryItem, Long vendorcategoryitemId) throws Exception {
		VendorAddCategoryItemManagement existingVendorAddCategoryItem = vendorAddCategoryItemManagementRepository.findById(vendorcategoryitemId).orElse(null);
        if (existingVendorAddCategoryItem == null) {
            throw new UserNotFoundException("Vendor not found");
        }

        existingVendorAddCategoryItem.setItemCategory(updatedVendorAddCategoryItem.getItemCategory());
        existingVendorAddCategoryItem.setItemColour(updatedVendorAddCategoryItem.getItemColour());
        existingVendorAddCategoryItem.setItemdelivery(updatedVendorAddCategoryItem.getItemdelivery());
        existingVendorAddCategoryItem.setItemName(updatedVendorAddCategoryItem.getItemName());
        existingVendorAddCategoryItem.setItemOffer(updatedVendorAddCategoryItem.getItemOffer());
        existingVendorAddCategoryItem.setItemPrice(updatedVendorAddCategoryItem.getItemPrice());
        existingVendorAddCategoryItem.setItemQTY(updatedVendorAddCategoryItem.getItemQTY());
        existingVendorAddCategoryItem.setItemSize(updatedVendorAddCategoryItem.getItemSize());
        
	return vendorAddCategoryItemManagementRepository.save(existingVendorAddCategoryItem);
	}

}
