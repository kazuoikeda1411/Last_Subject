package com.raisetech.lastsubject.service;

import com.raisetech.lastsubject.entity.MedicineInformation;
import com.raisetech.lastsubject.entity.PatientInformation;
import com.raisetech.lastsubject.exeption.ResourceNotFoundException;
import com.raisetech.lastsubject.mapper.MedicineNotebookMapper;
import com.raisetech.lastsubject.request.MedicineRequest;
import com.raisetech.lastsubject.request.PatientRequest;
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
		patientRequest.setUserName("Sato");
		patientRequest.setUserBirthdate(20000101);
		doReturn(medicineList).when(medicineNotebookMapper).findByPatient("Sato", 20000101);
		List<MedicineInformation> actual = medicineNotebookServiceImpl.findByPatient(patientRequest);
		assertThat(actual).isEqualTo(medicineList);
		verify(medicineNotebookMapper).findByPatient("Sato", 20000101);
	}

	@Test
	public void 指定したユーザー情報に当てはまるmedicineの情報がない時ResourceNotFoundExceptionが返されること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setUserName("");
		patientRequest.setUserBirthdate(20000101);
		assertThatThrownBy(() -> medicineNotebookServiceImpl.findByPatient(patientRequest)).isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void 患者情報を指定したときに正常にユーザーデータが登録されIDが返されること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setUserName("Sato");
		patientRequest.setUserBirthdate(20000101);
		doAnswer(invocation -> {
			(patientRequest).setId(1);
			return null;
		}).when(medicineNotebookMapper).postPatient(patientRequest);
		int actual = medicineNotebookServiceImpl.postPatient(patientRequest);
		assertThat(actual).isEqualTo(1);
		verify(medicineNotebookMapper).postPatient(patientRequest);
	}

	@Test
	public void PatientRequestで誤った生年月日が指定されたときIllegalArgumentExceptionが返されること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setUserName("Sato");
		patientRequest.setUserBirthdate(2000);
		assertThatThrownBy(() -> medicineNotebookServiceImpl.postPatient(patientRequest)).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	public void お薬情報を指定したとき正常にお薬情報が登録されIDが返されること() {
		MedicineRequest medicineRequest = new MedicineRequest();
		medicineRequest.setUserName("Sato");
		medicineRequest.setUserBirthdate(20000101);
		medicineRequest.setPharmacy("raiseTech_phamacy");
		medicineRequest.setMedicine("ibuprofen");
		doAnswer(invocation -> {
			(medicineRequest).setId(1);
			return null;
		}).when(medicineNotebookMapper).postMedicine(medicineRequest);
		int actual = medicineNotebookServiceImpl.postMedicine(medicineRequest);
		assertThat(actual).isEqualTo(1);
		verify(medicineNotebookMapper).postMedicine(medicineRequest);
	}

	@Test
	public void medicineRequestで誤った生年月日が指定されたときIllegalArgumentExceptionが返されること() {
		MedicineRequest medicineRequest = new MedicineRequest();
		medicineRequest.setId(1);
		medicineRequest.setUserName("Sato");
		medicineRequest.setUserBirthdate(2000);
		medicineRequest.setPharmacy("raiseTech_phamacy");
		medicineRequest.setMedicine("ibuprofen");
		assertThatThrownBy(() -> medicineNotebookServiceImpl.postMedicine(medicineRequest)).isInstanceOf(IllegalArgumentException.class);
	}
}
