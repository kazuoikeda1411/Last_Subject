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
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class MedicineNotebookMapperTest {
	@Autowired
	MedicineNotebookMapper medicineNotebookMapper;
	List patientList = List.of(new PatientInformation(1, "Sato", Date.valueOf("2000-01-01")), new PatientInformation(2, "tanaka", Date.valueOf("1999-04-01")), new PatientInformation(3, "nakamura", Date.valueOf("1998-08-01")));

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
	}

	@Test
	@DataSet(value="datasets/patient_informations.yml")
	@Transactional
	void 指定した患者情報の登録時に生成されたIdが返されていること() {
		PatientRequest patientRequest = new PatientRequest();
		patientRequest.setName("Sato");
		patientRequest.setBirthdate(20000101);
		int actual = medicineNotebookMapper.postPatient(patientRequest);
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
		int actual = medicineNotebookMapper.postMedicine(medicineRequest);
		assertThat(actual).isEqualTo(1);
	}
	@Test
	@ExpectedDataSet(value="datasets/patient_informations.yml", ignoreCols="id")
	public void 適切に患者情報が登録されていること() {
		PatientRequest patientRequest1 = new PatientRequest();
		patientRequest1.setName("Sato");
		patientRequest1.setBirthdate(20000101);
		PatientRequest patientRequest2 = new PatientRequest();
		patientRequest2.setName("tanaka");
		patientRequest2.setBirthdate(19990401);
		PatientRequest patientRequest3 = new PatientRequest();
		patientRequest3.setName("nakamura");
		patientRequest3.setBirthdate(19980801);
		medicineNotebookMapper.postPatient(patientRequest1);
		medicineNotebookMapper.postPatient(patientRequest2);
		medicineNotebookMapper.postPatient(patientRequest3);
	}
	@Test
	@DataSet(value="datasets/patient_informations.yml")
	@ExpectedDataSet(value="patient_medicine_informations.yml", ignoreCols="id")
	public void 適切にお薬情報が登録されていること() {
		MedicineRequest medicineRequest1 = new MedicineRequest();
		medicineRequest1.setName("Sato");
		medicineRequest1.setBirthdate(20000101);
		medicineRequest1.setPharmacy("raiseTech_phamacy");
		medicineRequest1.setMedicine("ibuprofen");
		MedicineRequest medicineRequest2 = new MedicineRequest();
		medicineRequest2.setName("tanaka");
		medicineRequest2.setBirthdate(19990401);
		medicineRequest2.setPharmacy("raiseTech_phamacy");
		medicineRequest2.setMedicine("PL顆粒");
		medicineNotebookMapper.postMedicine(medicineRequest1);
		medicineNotebookMapper.postMedicine(medicineRequest2);
	}
}
