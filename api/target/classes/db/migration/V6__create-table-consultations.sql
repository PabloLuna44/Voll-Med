CREATE TABLE consultations(
    id BIGINT NOT NULL  AUTO_INCREMENT,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    active TINYINT ,
    reason VARCHAR(100),
    consultation_date DATETIME,


    PRIMARY KEY (id),
    CONSTRAINT fk_consultation_doctor_id FOREIGN KEY (doctor_id) REFERENCES doctors(id),
    CONSTRAINT fk_consultation_patient_id FOREIGN KEY (patient_id) REFERENCES patients(id)

);