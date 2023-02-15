package com.raiseTech.lastSubject.service;

import com.raiseTech.lastSubject.entity.MedicineInformation;
import com.raiseTech.lastSubject.entity.PatientInformation;
import com.raiseTech.lastSubject.exeption.ResourceNotFoundException;
import com.raiseTech.lastSubject.mapper.MedicineNotebookMapper;
import com.raiseTech.lastSubject.request.MedicineRequest;
import com.raiseTech.lastSubject.request.PatientRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicineNotebookServiceImplTest {
	@InjectMocks
	MedicineNotebookServiceImpl medicineNotebookServiceImpl;
	@Mock
	MedicineNotebookMapper medicineNotebookMapper;
	List patientList = List.of(new PatientInformation(1, "Sato", Date.valueOf("2000-01-01")));
	List medicineList = List.of(new MedicineInformation(1, 1, Timestamp.valueOf("2023-01-03 00:00:00"), "raiseTech_phamacy", "ibuprofen"));

	@Test
	public void 患者情報を全件取得できること() {
		doReturn(patientList).when(medicineNotebookMapper).findAll();
		List<PatientInformation> actual = medicineNotebookServiceImpl.findAll();
		assertThat(actual).isEqualTo(patientList);
		verify(medicineNotebookMapper).findAll();
	}
	@Test
	public void 存在するユーザーの情報を指定したときに正常にユーザーデータが返されること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setName("Sato");
		patientRequest.setBirthdate(20000101);
		doReturn(medicineList).when(medicineNotebookMapper).findByPatient("Sato", 20000101);
		List<MedicineInformation> actual = medicineNotebookServiceImpl.findByPatient(patientRequest);
		assertThat(actual).isEqualTo(medicineList);
		verify(medicineNotebookMapper, times(2)).findByPatient("Sato", 20000101);
	}
	@Test
	public void 存在しないユーザーの情報が指定されたときResourceNotFoundExceptionが返されること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setName("");
		patientRequest.setBirthdate(20000101);
		assertThatThrownBy(() -> medicineNotebookServiceImpl.findByPatient(patientRequest)).isInstanceOf(ResourceNotFoundException.class);
	}
	@Test
	public void 患者情報を指定したときに正常にユーザーデータが登録されIDが返されること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setName("Sato");
		patientRequest.setBirthdate(20000101);
		doReturn(1).when(medicineNotebookMapper).postPatient(patientRequest);
		int actual = medicineNotebookServiceImpl.postPatient(patientRequest);
		assertThat(actual).isEqualTo(1);
		verify(medicineNotebookMapper).postPatient(patientRequest);
	}
	@Test
	public void 誤った生年月日が指定されたときResourceNotFoundExceptionが返されること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setName("Sato");
		patientRequest.setBirthdate(2000);
		assertThatThrownBy(() -> medicineNotebookServiceImpl.postPatient(patientRequest)).isInstanceOf(ResourceNotFoundException.class);
	}
	@Test
	public void お薬情報を指定したとき正常にお薬情報が登録されIDが返されること() {
		MedicineRequest medicineRequest = new MedicineRequest();
		medicineRequest.setId(1);
		medicineRequest.setName("Sato");
		medicineRequest.setBirthdate(20000101);
		medicineRequest.setPharmacy("raiseTech_phamacy");
		medicineRequest.setMedicine("ibuprofen");
		doReturn(1).when(medicineNotebookMapper).postMedicine(medicineRequest);
		int actual = medicineNotebookServiceImpl.postMedicine(medicineRequest);
		assertThat(actual).isEqualTo(1);
		verify(medicineNotebookMapper).postMedicine(medicineRequest);
	}
}