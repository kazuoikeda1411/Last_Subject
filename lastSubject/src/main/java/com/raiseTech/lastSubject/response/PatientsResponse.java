package com.raiseTech.lastSubject.response;

import com.raiseTech.lastSubject.entity.PatientInformation;
import lombok.Getter;

@Getter
public class PatientsResponse {
	private final int id;
	private final String name;
	private final java.sql.Date birthdate;
	public PatientsResponse(PatientInformation patientInformation) {
		this.id = patientInformation.id();
		this.name = patientInformation.name();
		this.birthdate = patientInformation.birthdate();
	}
}
