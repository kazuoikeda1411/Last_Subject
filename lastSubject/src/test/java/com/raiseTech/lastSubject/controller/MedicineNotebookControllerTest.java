package com.raiseTech.lastSubject.controller;

import com.raiseTech.lastSubject.entity.MedicineInformation;
import com.raiseTech.lastSubject.entity.PatientInformation;
import com.raiseTech.lastSubject.request.MedicineRequest;
import com.raiseTech.lastSubject.request.PatientRequest;
import com.raiseTech.lastSubject.response.MedicineResponse;
import com.raiseTech.lastSubject.response.PatientsResponse;
import com.raiseTech.lastSubject.service.MedicineNotebookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MedicineNotebookControllerTest {

	@InjectMocks
	MedicineNotebookController medicineNotebookController;
	@Mock
	MedicineNotebookService medicineNotebookService;

	List<PatientInformation> patientList = List.of(new PatientInformation(1, "Sato", Date.valueOf("2000-01-01")));
	List<PatientsResponse> expected = List.of(new PatientsResponse(1, "Sato", Date.valueOf("2000-01-01")));
	List medicineList = List.of(new MedicineInformation(1, 1, Timestamp.valueOf("2023-01-03 00:00:00"), "raiseTech_phamacy", "ibuprofen"));

	@Test
	public void 患者情報を全件取得できること() {
		doReturn(patientList).when(medicineNotebookService).findAll();
		List<PatientsResponse> actual = medicineNotebookController.getPatients();
//		assertThat(actual.get(0).getId()).isEqualTo(1);
//		assertThat(actual.get(0).getName()).isEqualTo("Sato");
//		assertThat(actual.get(0).getBirthdate()).isEqualTo(Date.valueOf("2000-01-01"));
//		assertThat(actual).isEqualTo(expected);
		assertThat(actual).isEqualTo(expected);
		verify(medicineNotebookService).findAll();
	}

	@Test
	public void 存在するユーザーの情報を指定したときに正常にユーザーデータが返されること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setName("Sato");
		patientRequest.setBirthdate(20000101);
		doReturn(medicineList).when(medicineNotebookService).findByPatient(patientRequest);
		List<MedicineResponse> actual = medicineNotebookController.getMedicine(patientRequest);
		assertThat(actual.get(0).getVisitDate()).isEqualTo(Timestamp.valueOf("2023-01-03 00:00:00"));
		assertThat(actual.get(0).getPharmacy()).isEqualTo("raiseTech_phamacy");
		assertThat(actual.get(0).getMedicine()).isEqualTo("ibuprofen");
		verify(medicineNotebookService).findByPatient(patientRequest);
	}

	@Test
	public void 患者情報を指定したときに適切なIDとBODYが返されること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setId(1);
		patientRequest.setName("Sato");
		patientRequest.setBirthdate(20000101);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		doReturn(1).when(medicineNotebookService).postPatient(patientRequest);
		ResponseEntity actual = medicineNotebookController.postPatient(patientRequest, uriBuilder);
		assertThat(patientRequest.getId()).isEqualTo(1);
		verify(medicineNotebookService).postPatient(patientRequest);
		assertThat(actual.getBody().toString()).isEqualTo("{id=1, message=patient successfully created}");
	}

	@Test
	public void お薬情報を指定したとき適切なIDとBODYが返されること() {
		MedicineRequest medicineRequest = new MedicineRequest();
		medicineRequest.setId(1);
		medicineRequest.setName("Sato");
		medicineRequest.setBirthdate(20000101);
		medicineRequest.setPharmacy("raiseTech_phamacy");
		medicineRequest.setMedicine("ibuprofen");
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();
		doReturn(1).when(medicineNotebookService).postMedicine(medicineRequest);
		ResponseEntity actual = medicineNotebookController.postMedicine(medicineRequest, uriBuilder);
		assertThat(medicineRequest.getId()).isEqualTo(1);
		assertThat(actual.getBody().toString()).isEqualTo("{id=1, message=medicine successfully created}");
		verify(medicineNotebookService).postMedicine(medicineRequest);
	}
}
