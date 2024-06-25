package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.VendorAddCategoryItemManagement;

public interface VendorAddCategoryItemManagementRepositoryImpl {

	VendorAddCategoryItemManagement saveCityStateManagement(VendorAddCategoryItemManagement vendorAddCategoryItemManagement) throws Exception;

	List<VendorAddCategoryItemManagement> getAllVendorAddCategoryItemManagement();

	VendorAddCategoryItemManagement getVendorAddCategoryItemManagementById(Long vendorcategoryitemId);

	void deleteVendorAddCategoryItemManagementById(Long vendorcategoryitemId);

	VendorAddCategoryItemManagement updatedVendorAddCategoryItemManagement(VendorAddCategoryItemManagement updatedVendorAddCategoryItem, Long vendorcategoryitemId) throws Exception;

}
