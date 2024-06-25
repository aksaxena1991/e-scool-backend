package com.eduConnect.eduConnect.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendorAddCategoryItemManagement")
public class VendorAddCategoryItemManagement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vendorcategoryitemId;

	@Column(name = "vendorcategoryitem_req_date_time")
	private LocalDate vendorcategoryitemRegDateTime;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "item_size")
	private String itemSize;

	@Column(name = "item_price")
	private double itemPrice;

	@Column(name = "item_colour")
	private String itemColour;

	@Column(name = "item_qty")
	private Integer itemQTY;

	@Column(name = "item_delivery")
	private String itemdelivery;

	@Column(name = "item_offer")
	private double itemOffer;

	@Column(name = "item_category")
	private String itemCategory;

	@ManyToOne(fetch = FetchType.EAGER) // Eager loading
	@JoinColumn(name = "userId")
	private UsersManagement usersManagement;

	public Long getVendorcategoryitemId() {
		return vendorcategoryitemId;
	}

	public void setVendorcategoryitemId(Long vendorcategoryitemId) {
		this.vendorcategoryitemId = vendorcategoryitemId;
	}

	public LocalDate getVendorcategoryitemRegDateTime() {
		return vendorcategoryitemRegDateTime;
	}

	public void setVendorcategoryitemRegDateTime(LocalDate vendorcategoryitemRegDateTime) {
		this.vendorcategoryitemRegDateTime = vendorcategoryitemRegDateTime;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemSize() {
		return itemSize;
	}

	public void setItemSize(String itemSize) {
		this.itemSize = itemSize;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemColour() {
		return itemColour;
	}

	public void setItemColour(String itemColour) {
		this.itemColour = itemColour;
	}

	public Integer getItemQTY() {
		return itemQTY;
	}

	public void setItemQTY(Integer itemQTY) {
		this.itemQTY = itemQTY;
	}

	public String getItemdelivery() {
		return itemdelivery;
	}

	public void setItemdelivery(String itemdelivery) {
		this.itemdelivery = itemdelivery;
	}

	public double getItemOffer() {
		return itemOffer;
	}

	public void setItemOffer(double itemOffer) {
		this.itemOffer = itemOffer;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public UsersManagement getUsersManagement() {
		return usersManagement;
	}

	public void setUsersManagement(UsersManagement usersManagement) {
		this.usersManagement = usersManagement;
	}

}
