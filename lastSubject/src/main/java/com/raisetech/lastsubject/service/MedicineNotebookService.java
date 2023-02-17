package com.raisetech.lastsubject.service;

import com.raisetech.lastsubject.entity.MedicineInformation;
import com.raisetech.lastsubject.entity.PatientInformation;
import com.raisetech.lastsubject.request.MedicineRequest;
import com.raisetech.lastsubject.request.PatientRequest;

import java.util.List;

public interface MedicineNotebookService {
	List<PatientInformation> findAll();
	List<MedicineInformation> findByPatient(PatientRequest patientRequest);
	int postPatient(PatientRequest patientRequest);
	int postMedicine(MedicineRequest medicineRequest);
}
