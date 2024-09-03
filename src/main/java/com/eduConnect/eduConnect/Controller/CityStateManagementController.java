package com.eduConnect.eduConnect.Controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eduConnect.eduConnect.Dto.ResponseMessageDto;
import com.eduConnect.eduConnect.Exception.UserNotFoundException;
import com.eduConnect.eduConnect.Model.CityStateManagement;
import com.eduConnect.eduConnect.Repositroy.CityStateManagementRepository;
import com.eduConnect.eduConnect.RespositroyImpl.CityStateManagementRepositoryImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/city-state")
public class CityStateManagementController {

	@Autowired
	private CityStateManagementRepositoryImpl cityStateManagementRepositoryImpl;
	
	@Autowired
	private CityStateManagementRepository cityStateManagementRepository;
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessageDto> createCityState(@RequestBody CityStateManagement cityStateManagement) {
        try {
        	CityStateManagement savedCityState = cityStateManagementRepositoryImpl.saveCityStateManagement(cityStateManagement);
            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "City-State saved successfully", null, new Date());
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to save City-State", e.getMessage(), new Date());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }
	
	 @RequestMapping(value ="/update/{citystateId}", method = RequestMethod.POST)
	    public ResponseEntity<ResponseMessageDto> updateCityState(@PathVariable Long citystateId, @RequestBody CityStateManagement updatedCityState) {
	        try {
	        	CityStateManagement updatedCityStates = cityStateManagementRepositoryImpl.updatedCityStateManagement(updatedCityState, citystateId);
	            ResponseMessageDto responseMessage = new ResponseMessageDto("success", "City-State updated successfully", null, new Date());
	            return ResponseEntity.ok(responseMessage);
	        } catch (UserNotFoundException e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "City-State not found", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
	        } catch (Exception e) {
	            ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to update City-State", e.getMessage(), new Date());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
	        }
	    }

	 @RequestMapping(value = "/details", method = RequestMethod.GET)
		public ResponseEntity<Object> getAllCityState() {
			List<CityStateManagement> cityStateManagements = cityStateManagementRepositoryImpl.getAllCityStateManagement();
			if (cityStateManagements.isEmpty()) {
				ResponseMessageDto responseMessage = new ResponseMessageDto("error", "No City-State found", "City-State list is empty",
						new Date());
				return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
			} else {
				return ResponseEntity.ok(cityStateManagements);
			}
		}
	 
	 @RequestMapping(value = "/details/{citystateId}", method = RequestMethod.GET)
		public ResponseEntity<Object> getCitystateId(@PathVariable Long citystateId) {
			try {
				CityStateManagement cityStateManagement = cityStateManagementRepositoryImpl.getCityStateManagementById(citystateId);

				  if (cityStateManagement == null) {
			            throw new RuntimeException("City-State details not found");
			        }
				  
				return ResponseEntity.ok(cityStateManagement);
			} catch (RuntimeException e) {
				ResponseMessageDto responseMessage = new ResponseMessageDto("error", "City-State details not found",
						e.getMessage(), new Date());
				return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
			}
		}
		
	 @RequestMapping(value = "/delete/{citystateId}", method = RequestMethod.GET)
		public ResponseEntity<ResponseMessageDto> deleteCityState(@PathVariable Long citystateId) {
			try {
				cityStateManagementRepositoryImpl.deleteCityStateManagementById(citystateId);
				ResponseMessageDto responseMessage = new ResponseMessageDto("success", "City-State successfully deleted", null,
						new Date());
				return ResponseEntity.ok(responseMessage);
			} catch (Exception e) {
				ResponseMessageDto responseMessage = new ResponseMessageDto("error", "Failed to delete City-State",
						e.getMessage(), new Date());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
			}
		}
	 
	 @RequestMapping(value = "/count", method = RequestMethod.GET)
		public long getTotalCityStateCount() {
			long totalCityState = cityStateManagementRepository.count();
			return totalCityState;
		}
}
