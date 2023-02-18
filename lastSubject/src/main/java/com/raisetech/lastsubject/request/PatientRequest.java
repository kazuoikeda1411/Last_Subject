package com.raisetech.lastsubject.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientRequest {
	private int id;
	private String userName;
	private int userBirthdate;
	public PatientRequest(String userName, int userBirthdate) {
		this.userName = userName;
		this.userBirthdate = userBirthdate;
	}
	public PatientRequest() {
	}
}
