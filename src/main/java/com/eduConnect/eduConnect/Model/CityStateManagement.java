package com.eduConnect.eduConnect.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cityStateManagement")
public class CityStateManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long citystateId;

	@Column(name = "citystate_req_date_time")
	private LocalDate citystateRegDateTime;

	@Column(name = "city_name")
	private String cityName;

	@Column(name = "state_name")
	private String stateName;

	@Column(name = "country_name")
	private String countryName;

	public Long getCitystateId() {
		return citystateId;
	}

	public void setCitystateId(Long citystateId) {
		this.citystateId = citystateId;
	}

	public LocalDate getCitystateRegDateTime() {
		return citystateRegDateTime;
	}

	public void setCitystateRegDateTime(LocalDate citystateRegDateTime) {
		this.citystateRegDateTime = citystateRegDateTime;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}
