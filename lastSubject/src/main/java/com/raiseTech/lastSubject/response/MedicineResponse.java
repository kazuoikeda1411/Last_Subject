package com.raiseTech.lastSubject.response;

import com.raiseTech.lastSubject.entity.MedicineInformation;

import java.sql.Timestamp;

public class MedicineResponse {
	private final Timestamp visitDate;
	private final String pharmacy;
	private final String medicine;
	public MedicineResponse(MedicineInformation medicineInformation) {
		this.visitDate = medicineInformation.visitDate();
		this.pharmacy = medicineInformation.pharmacy();
		this.medicine = medicineInformation.medicine();
	}
	public Timestamp getVisitDate() {
		return visitDate;
	}
	public String getPharmacy() {
		return pharmacy;
	}
	public String getMedicine() {
		return medicine;
	}
}
