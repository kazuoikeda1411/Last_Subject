package com.raisetech.lastsubject.request;

import lombok.Getter;

@Getter
//@Setter
public class MedicineRequest {
	private int id;
	private int userId;
	private String name;
	private int birthdate;
	private String pharmacy;
	private String medicine;
	public MedicineRequest(String name, int birthdate, String pharmacy, String medicine) {
		this.birthdate = birthdate;
		this.name = name;
		this.pharmacy = pharmacy;
		this.medicine = medicine;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBirthdate(int birthdate) {
		this.birthdate = birthdate;
	}
	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public MedicineRequest() {
	}
	;
}
