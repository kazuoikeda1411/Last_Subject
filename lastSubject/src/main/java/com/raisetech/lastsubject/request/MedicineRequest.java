package com.raisetech.lastsubject.request;

import lombok.Getter;

@Getter
//@Setter
public class MedicineRequest {
	private int id;
	private int userId;
	private String userName;
	private int userBirthdate;
	private String pharmacy;
	private String medicine;
	public MedicineRequest(String userName, int userBirthdate, String pharmacy, String medicine) {
		this.userBirthdate = userBirthdate;
		this.userName = userName;
		this.pharmacy = pharmacy;
		this.medicine = medicine;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserBirthdate(int userBirthdate) {
		this.userBirthdate = userBirthdate;
	}
	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public MedicineRequest() {
	}
}
