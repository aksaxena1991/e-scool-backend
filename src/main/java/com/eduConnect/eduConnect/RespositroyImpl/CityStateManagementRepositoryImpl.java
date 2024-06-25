package com.eduConnect.eduConnect.RespositroyImpl;

import java.util.List;

import com.eduConnect.eduConnect.Model.CityStateManagement;

public interface CityStateManagementRepositoryImpl {

	CityStateManagement saveCityStateManagement(CityStateManagement cityStateManagement) throws Exception;

	List<CityStateManagement> getAllCityStateManagement();

	CityStateManagement getCityStateManagementById(Long citystateId);

	void deleteCityStateManagementById(Long citystateId);

	CityStateManagement updatedCityStateManagement(CityStateManagement updatedCityState, Long citystateId) throws Exception;

}
