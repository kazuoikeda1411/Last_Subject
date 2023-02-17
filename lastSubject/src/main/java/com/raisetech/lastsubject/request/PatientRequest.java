package com.raisetech.lastsubject.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequest {
	private int id;
	private String name;
	private int birthdate;
	public PatientRequest(String name, int birthdate) {
		this.name = name;
		this.birthdate = birthdate;
	}
	public PatientRequest() {
	}
}
