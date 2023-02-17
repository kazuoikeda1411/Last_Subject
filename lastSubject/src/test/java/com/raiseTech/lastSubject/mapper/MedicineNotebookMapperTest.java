package com.raiseTech.lastSubject.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import com.raiseTech.lastSubject.entity.MedicineInformation;
import com.raiseTech.lastSubject.entity.PatientInformation;
import com.raiseTech.lastSubject.request.MedicineRequest;
import com.raiseTech.lastSubject.request.PatientRequest;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class MedicineNotebookMapperTest {
	@Autowired
	MedicineNotebookMapper medicineNotebookMapper;
	List patientList = List.of(new PatientInformation(1, "Sato", Date.valueOf("2000-01-01")), new PatientInformation(2, "tanaka", Date.valueOf("1999-04-01")), new PatientInformation(3, "nakamura", Date.valueOf("1998-08-01")));
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	List medicineList = List.of(new MedicineInformation(1, 1, timestamp, "raiseTech_phamacy", "ibuprofen"));

	@Test
	@DataSet(value="datasets/patient_informations.yml")
	@Transactional
	void 全ての患者情報が取得できること() {
		List<PatientInformation> actual = medicineNotebookMapper.findAll();
		assertThat(actual.size()).isEqualTo(3);
		assertThat(actual).isEqualTo(patientList);
	}

	@Test
	@DataSet(value="datasets/patient_informations.yml,datasets/patient_medicine_informations.yml")
	@Transactional
	void 指定した情報を元に正確なお薬情報を取得できること() {
		List<MedicineInformation> actual = medicineNotebookMapper.findByPatient("Sato", 20000101);
		assertThat(actual.size()).isEqualTo(1);
		assertThat(actual).isEqualTo(medicineList);
	}

	@Test
	@DataSet(value="datasets/patient_informations.yml")
	@Transactional
	void 指定した患者情報の登録時に生成されたIdが返されていること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setName("Sato");
		patientRequest.setBirthdate(20000101);
		medicineNotebookMapper.postPatient(patientRequest);
		int actual = patientRequest.getId();
		assertThat(actual).isEqualTo(1);
	}

	@Test
	@DataSet(value="datasets/patient_informations.yml,datasets/patient_medicine_informations.yml")
	@Transactional
	void 指定した患者情報とお薬情報を元に情報を登録して生成されたIdが返されること() {
		MedicineRequest medicineRequest = new MedicineRequest();
		medicineRequest.setName("Sato");
		medicineRequest.setBirthdate(20000101);
		medicineRequest.setPharmacy("raiseTech_phamacy");
		medicineRequest.setMedicine("ibuprofen");
		medicineNotebookMapper.postMedicine(medicineRequest);
		int actual = medicineRequest.getId();
		assertThat(actual).isEqualTo(1);
	}

	@Test
	@ExpectedDataSet(value="datasets/patient_informations.yml", ignoreCols="id")
	public void 適切に患者情報が登録されていること() {
		List<PatientRequest> patientRequests = Arrays.asList(new PatientRequest("Sato", 20000101), new PatientRequest("tanaka", 19990401), new PatientRequest("nakamura", 19980801));
		medicineNotebookMapper.postPatient(patientRequests.get(0));
		medicineNotebookMapper.postPatient(patientRequests.get(1));
		medicineNotebookMapper.postPatient(patientRequests.get(2));
	}

	@Test
	@DataSet(value="datasets/patient_informations.yml")
	@ExpectedDataSet(value="patient_medicine_informations.yml", ignoreCols="id")
	public void 適切にお薬情報が登録されていること() {
		List<MedicineRequest> medicineRequests = Arrays.asList(new MedicineRequest("Sato", 20000101, "raiseTech_pharmacy", "ibuprofen"), new MedicineRequest("tanaka", 19990401, "raiseTech_pharmacy", "PL顆粒"));
		medicineNotebookMapper.postMedicine(medicineRequests.get(0));
		medicineNotebookMapper.postMedicine(medicineRequests.get(1));
	}
}
