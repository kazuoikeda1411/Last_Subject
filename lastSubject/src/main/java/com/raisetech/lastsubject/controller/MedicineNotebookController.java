package com.raisetech.lastsubject.controller;

import com.raisetech.lastsubject.request.MedicineRequest;
import com.raisetech.lastsubject.request.PatientRequest;
import com.raisetech.lastsubject.response.MedicineResponse;
import com.raisetech.lastsubject.response.PatientsResponse;
import com.raisetech.lastsubject.service.MedicineNotebookService;
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
		int id = medicineNotebookService.postPatient(patientRequest);
		URI url = uriBuilder.path("/patients/" + id).build().toUri();
		return ResponseEntity.created(url).body(Map.of("id", id, "message", "patient successfully created"));
	}

	@PostMapping("/medicines")
	public ResponseEntity postMedicine(@RequestBody MedicineRequest medicineRequest, UriComponentsBuilder uriComponentsBuilder) {
		int id = medicineNotebookService.postMedicine(medicineRequest);
		URI url = uriComponentsBuilder.path("/medicine/" + id).build().toUri();
		return ResponseEntity.created(url).body(Map.of("id", id, "message", "medicine successfully created"));
	}
}
