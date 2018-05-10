package com.wavelet.stockmovement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String location;
	@NotBlank
	private String date;
	@NotBlank
	private String item;
	@PositiveOrZero
	@NotNull
	private Long inQty;
	@PositiveOrZero
	@NotNull
	private Long outQty;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Long getInQty() {
		return inQty;
	}
	public void setInQty(Long inQty) {
		this.inQty = inQty;
	}
	public Long getOutQty() {
		return outQty;
	}
	public void setOutQty(Long outQty) {
		this.outQty = outQty;
	}
}
