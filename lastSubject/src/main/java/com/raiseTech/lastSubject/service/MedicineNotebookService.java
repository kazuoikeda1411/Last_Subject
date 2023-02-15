package com.raiseTech.lastSubject.service;

import com.raiseTech.lastSubject.entity.MedicineInformation;
import com.raiseTech.lastSubject.entity.PatientInformation;
import com.raiseTech.lastSubject.request.MedicineRequest;
import com.raiseTech.lastSubject.request.PatientRequest;

import java.util.List;

public interface MedicineNotebookService {
	List<PatientInformation> findAll();
	List<MedicineInformation> findByPatient(PatientRequest patientRequest);
	int postPatient(PatientRequest patientRequest);
	int postMedicine(MedicineRequest medicineRequest);
}
