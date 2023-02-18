package com.raisetech.lastsubject.response;

import com.raisetech.lastsubject.entity.PatientInformation;
import lombok.Getter;

import java.sql.Date;

@Getter
public class PatientsResponse {
	private final int id;
	private final String userName;
	private final Date userBirthdate;
	public PatientsResponse(PatientInformation patientInformation) {
		this.id = patientInformation.id();
		this.userName = patientInformation.userName();
		this.userBirthdate = patientInformation.userBirthdate();
	}
	public PatientsResponse(int id, String userName, Date userBirthdate) {
		this.id = id;
		this.userName = userName;
		this.userBirthdate = userBirthdate;
	}
}
