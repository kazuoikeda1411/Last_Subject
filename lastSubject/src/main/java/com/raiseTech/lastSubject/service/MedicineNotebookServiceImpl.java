package com.raiseTech.lastSubject.service;

import com.raiseTech.lastSubject.entity.MedicineInformation;
import com.raiseTech.lastSubject.entity.PatientInformation;
import com.raiseTech.lastSubject.exeption.ResourceNotFoundException;
import com.raiseTech.lastSubject.mapper.MedicineNotebookMapper;
import com.raiseTech.lastSubject.request.MedicineRequest;
import com.raiseTech.lastSubject.request.PatientRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineNotebookServiceImpl implements MedicineNotebookService {
	private final MedicineNotebookMapper medicineNotebookMapper;
	@Override
	public List<PatientInformation> findAll() {
		return medicineNotebookMapper.findAll();
	}
	@Override
	public List<MedicineInformation> findByPatient(PatientRequest patientRequest) {
		if (this.medicineNotebookMapper.findByPatient(patientRequest.getName(), patientRequest.getBirthdate()).isEmpty()) {
			throw new ResourceNotFoundException("resource not found");
		}
		return this.medicineNotebookMapper.findByPatient(patientRequest.getName(), patientRequest.getBirthdate());
	}
	@Override
	public int postPatient(PatientRequest patientRequest) {
		if (String.valueOf(patientRequest.getBirthdate()).length() != 8) {
			throw new ResourceNotFoundException("resource not found");
		} else {
			return this.medicineNotebookMapper.postPatient(patientRequest);
		}
	}
	@Override
	public int postMedicine(MedicineRequest medicineRequest) {
		if (String.valueOf(medicineRequest.getBirthdate()).length() != 8) {
			throw new ResourceNotFoundException("resource not found");
		} else {
			return this.medicineNotebookMapper.postMedicine(medicineRequest);
		}
	}
}
