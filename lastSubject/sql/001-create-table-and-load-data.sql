DROP TABLE IF EXISTS patient_informations;

CREATE TABLE patient_informations(
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  birthdate date NOT NULL,
  PRIMARY KEY(id)
);

DROP TABLE IF EXISTS patient_medicine_informations;
CREATE TABLE patient_medicine_informations(
  id int unsigned AUTO_INCREMENT,
  visitDate smalldatetime NOT NULL,
  pharmacy VARCHAR(255) NOT NULL,
  medicine VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

INSERT INTO patient_informations (name,birthdate) VALUES ("Sato",2000-01-01);
INSERT INTO patient_medicine_informations (visitDate,pharmacy,medicine) VALUES (now(),raiseTech_phamacy,
ibuprofen);
