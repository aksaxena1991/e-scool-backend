package com.eduConnect.eduConnect.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.CityStateManagement;
import com.eduConnect.eduConnect.Model.UsersManagement;
import com.eduConnect.eduConnect.Repositroy.CityStateManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.CityStateManagementRepositoryImpl;


@Service
public class CityStateManagementService implements CityStateManagementRepositoryImpl {

	@Autowired
	private CityStateManagementRepository cityStateManagementRepository;
	
	@Override
	public CityStateManagement saveCityStateManagement(CityStateManagement cityStateManagement) throws Exception {
		cityStateManagement.setCitystateRegDateTime(LocalDate.now());
		return cityStateManagementRepository.save(cityStateManagement);
	}

	@Override
	public List<CityStateManagement> getAllCityStateManagement() {
		return this.cityStateManagementRepository.findAll();
	}

	@Override
	public CityStateManagement getCityStateManagementById(Long citystateId) {
		try {
			return cityStateManagementRepository.findById(citystateId).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteCityStateManagementById(Long citystateId) {
		this.cityStateManagementRepository.deleteById(citystateId);
	}

	@Override
	public CityStateManagement updatedCityStateManagement(CityStateManagement updatedCityState, Long citystateId)
			throws Exception {
		CityStateManagement existingCityState = cityStateManagementRepository.findById(citystateId).orElse(null);
	        if (existingCityState == null) {
	            throw new UserNotFoundException("City-State not found");
	        }

	        existingCityState.setCityName(updatedCityState.getCityName());
	        existingCityState.setCountryName(updatedCityState.getCountryName());
	        existingCityState.setStateName(updatedCityState.getStateName());
		return cityStateManagementRepository.save(existingCityState);
	}

}
