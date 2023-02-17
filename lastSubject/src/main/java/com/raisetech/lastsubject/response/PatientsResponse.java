package com.raisetech.lastsubject.response;

import com.raisetech.lastsubject.entity.PatientInformation;
import lombok.Getter;

import java.sql.Date;

@Getter
public class PatientsResponse {
	private final int id;
	private final String name;
	private final Date birthdate;
	public PatientsResponse(PatientInformation patientInformation) {
		this.id = patientInformation.id();
		this.name = patientInformation.name();
		this.birthdate = patientInformation.birthdate();
	}
	public PatientsResponse(int id, String name, Date birthdate) {
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}
}
