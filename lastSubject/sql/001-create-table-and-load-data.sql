DROP TABLE IF EXISTS patient_informations;

CREATE TABLE patient_informations(
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  birthdate DATE NOT NULL,
  PRIMARY KEY(id)
);

DROP TABLE IF EXISTS patient_medicine_informations;

CREATE TABLE patient_medicine_informations(
  id int unsigned AUTO_INCREMENT,
  userId int NOT NULL,
  visitDate datetime NOT NULL,
  pharmacy VARCHAR(255) NOT NULL,
  medicine VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
);

--INSERT INTO patient_informations (name,birthdate) VALUES ("Sato",20000101);
--INSERT INTO patient_medicine_informations (userId,visitDate,pharmacy,medicine) VALUES (1,now(),"raiseTech_phamacy",
--"ibuprofen");
