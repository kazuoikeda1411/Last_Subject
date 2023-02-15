package com.raiseTech.lastSubject.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineRequest {
	private int id;
	private int userId;
	private String name;
	private int birthdate;
	private String pharmacy;
	private String medicine;
}
