package com.eduConnect.eduConnect.Model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "usersManagement")
public class UsersManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "user_add_date_time")
	private LocalDate usersAddDateTime;

	@Column(name = "user_name")
	private String userName;

	@Column(unique = true)
	@NotEmpty(message = "Email must not be empty")
	private String email;

	@Column(name = "number")
	private String number;

	@Column(name = "password")
	private String password;

	@Column(name = "otp")
	private String otp;

	@Column(name = "login_status")
	private String loginStatus;

	@Column(name = "role")
	private String role;

	@OneToMany(mappedBy = "usersManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<VendorAddCategoryItemManagement> vendorItem;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getUsersAddDateTime() {
		return usersAddDateTime;
	}

	public void setUsersAddDateTime(LocalDate usersAddDateTime) {
		this.usersAddDateTime = usersAddDateTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<VendorAddCategoryItemManagement> getVendorItem() {
		return vendorItem;
	}

	public void setVendorItem(List<VendorAddCategoryItemManagement> vendorItem) {
		this.vendorItem = vendorItem;
	}

}
