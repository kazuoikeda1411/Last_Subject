package com.raisetech.lastsubject.mapper;

import com.raisetech.lastsubject.entity.MedicineInformation;
import com.raisetech.lastsubject.entity.PatientInformation;
import com.raisetech.lastsubject.request.MedicineRequest;
import com.raisetech.lastsubject.request.PatientRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MedicineNotebookMapper {
	List<PatientInformation> findAll();
	List<MedicineInformation> findByPatient(@Param("userName") String userName, @Param("userBirthdate") int userBirthdate);
	void postPatient(PatientRequest patientRequest);
	void postMedicine(MedicineRequest medicineRequest);
}
