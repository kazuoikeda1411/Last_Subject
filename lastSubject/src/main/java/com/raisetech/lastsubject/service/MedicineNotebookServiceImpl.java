package com.raisetech.lastsubject.service;

import com.raisetech.lastsubject.entity.MedicineInformation;
import com.raisetech.lastsubject.entity.PatientInformation;
import com.raisetech.lastsubject.exeption.ResourceNotFoundException;
import com.raisetech.lastsubject.mapper.MedicineNotebookMapper;
import com.raisetech.lastsubject.request.MedicineRequest;
import com.raisetech.lastsubject.request.PatientRequest;
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
		if (patientRequest.getUserName().isEmpty() || String.valueOf(patientRequest.getUserBirthdate()).isEmpty()) {
			throw new ResourceNotFoundException("resource not found");
		}
		return this.medicineNotebookMapper.findByPatient(patientRequest.getUserName(), patientRequest.getUserBirthdate());
	}

	@Override
	public int postPatient(PatientRequest patientRequest) {
		if (String.valueOf(patientRequest.getUserBirthdate()).length() != 8) {
			throw new IllegalArgumentException("userBirthdate is invalid value");
		} else {
			this.medicineNotebookMapper.postPatient(patientRequest);
			return patientRequest.getId();
		}
	}
	@Override
	public int postMedicine(MedicineRequest medicineRequest) {
		if (String.valueOf(medicineRequest.getUserBirthdate()).length() != 8) {
			throw new IllegalArgumentException("userBirthdate is invalid value");
		} else {
			this.medicineNotebookMapper.postMedicine(medicineRequest);
			return medicineRequest.getId();
		}
	}
}
