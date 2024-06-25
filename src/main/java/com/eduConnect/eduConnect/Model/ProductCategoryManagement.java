package com.eduConnect.eduConnect.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productCategoryManagement")
public class ProductCategoryManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productcategoryId;

	@Column(name = "productcategory_req_date_time")
	private LocalDate productcategoryRegDateTime;

	@Column(name = "productcategory_name")
	private String productcategoryName;

	@Column(name = "peoplecategory_name")
	private String peoplecategoryName;

	public Long getProductcategoryId() {
		return productcategoryId;
	}

	public void setProductcategoryId(Long productcategoryId) {
		this.productcategoryId = productcategoryId;
	}

	public LocalDate getProductcategoryRegDateTime() {
		return productcategoryRegDateTime;
	}

	public void setProductcategoryRegDateTime(LocalDate productcategoryRegDateTime) {
		this.productcategoryRegDateTime = productcategoryRegDateTime;
	}

	public String getProductcategoryName() {
		return productcategoryName;
	}

	public void setProductcategoryName(String productcategoryName) {
		this.productcategoryName = productcategoryName;
	}

	public String getPeoplecategoryName() {
		return peoplecategoryName;
	}

	public void setPeoplecategoryName(String peoplecategoryName) {
		this.peoplecategoryName = peoplecategoryName;
	}

}
