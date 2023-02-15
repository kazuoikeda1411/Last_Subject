package com.raiseTech.lastSubject.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequest {
	private int id;
	private String name;
	private int birthdate;
	public PatientRequest() {
	}
}
