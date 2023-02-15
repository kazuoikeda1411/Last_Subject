package com.raiseTech.lastSubject.controller;

import com.raiseTech.lastSubject.request.MedicineRequest;
import com.raiseTech.lastSubject.request.PatientRequest;
import com.raiseTech.lastSubject.response.MedicineResponse;
import com.raiseTech.lastSubject.response.PatientsResponse;
import com.raiseTech.lastSubject.service.MedicineNotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MedicineNotebookController {
	private final MedicineNotebookService medicineNotebookService;
	@GetMapping("/patients")
	public List<PatientsResponse> getPatients() {
		return medicineNotebookService.findAll().stream().map(PatientsResponse::new).toList();
	}
	@GetMapping("/medicines")
	public List<MedicineResponse> getMedicine(@RequestBody PatientRequest patientRequest) {
		return medicineNotebookService.findByPatient(patientRequest).stream().map(MedicineResponse::new).toList();
	}
	@PostMapping("/patients")
	public ResponseEntity postPatient(@RequestBody PatientRequest patientRequest, UriComponentsBuilder uriBuilder) {
		medicineNotebookService.postPatient(patientRequest);
		URI url = uriBuilder.path("/patients/" + patientRequest.getId()).build().toUri();
		return ResponseEntity.created(url).body(Map.of("id", patientRequest.getId(), "message", "patient successfully created"));
	}
	@PostMapping("/medicines")
	public ResponseEntity postMedicine(@RequestBody MedicineRequest medicineRequest, UriComponentsBuilder uriComponentsBuilder) {
		medicineNotebookService.postMedicine(medicineRequest);
		URI url = uriComponentsBuilder.path("/medicine/" + medicineRequest.getId()).build().toUri();
		return ResponseEntity.created(url).body(Map.of("id", medicineRequest.getId(), "message", "medicine successfully created"));
	}
}
