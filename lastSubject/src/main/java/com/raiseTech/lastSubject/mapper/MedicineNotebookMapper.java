package com.raiseTech.lastSubject.mapper;

import com.raiseTech.lastSubject.entity.MedicineInformation;
import com.raiseTech.lastSubject.entity.PatientInformation;
import com.raiseTech.lastSubject.request.MedicineRequest;
import com.raiseTech.lastSubject.request.PatientRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MedicineNotebookMapper {
	List<PatientInformation> findAll();
	List<MedicineInformation> findByPatient(@Param("name") String name, @Param("birthdate") int birthdate);
	void postPatient(PatientRequest patientRequest);
	void postMedicine(MedicineRequest medicineRequest);
}
