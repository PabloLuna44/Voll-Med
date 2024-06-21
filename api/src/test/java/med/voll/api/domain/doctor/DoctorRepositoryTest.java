package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("It should return null when Doctor has a consult with other patient in that time")
    void findDoctorWithSpecialtyInDateTest1() {

        //Given
        var nextMonday= LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var doctor=registerDoctor("Pablo","pablo@gmail.com","1234567890","098765",Specialty.PEDIATRIC,new Address("1","1","1","1","1","1"));
        var patient=registerPatient("mateo","mateo@gmail.com","9878906578",new Address("1","1","1","1","1","1"));
        registerConsut(doctor,patient,nextMonday);

        //When
        Optional<Doctor> doctorFree=doctorRepository.findDoctorWithSpecialtyInDate(Specialty.PEDIATRIC,nextMonday);

        //Then
        assertThat(doctorFree).isEmpty();

    }


    @Test
    @DisplayName("It should return a Doctor when realice query to repository")
    void findDoctorWithSpecialtyInDateTest2() {

        //Given
        var nextMonday= LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        Doctor doctor=registerDoctor("Pablo","pablo@gmail.com","1234567890","098765",Specialty.PEDIATRIC,new Address("1","1","1","1","1","1"));

        //When
        Optional<Doctor> doctorFree=doctorRepository.findDoctorWithSpecialtyInDate(Specialty.PEDIATRIC,nextMonday);

        //Then
        assertThat(doctorFree.get()).isEqualTo(doctor);

    }

    private Doctor registerDoctor(String name, String email, String phone,String document, Specialty specialty,Address address) {
        Doctor doctor = new Doctor(null,name,email,phone,document,true,specialty,address);
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String phone,Address address) {
        Patient patient = new Patient(null,name,email,phone,true,address);
        em.persist(patient);
        return patient;
    }

    private Consultation registerConsut(Doctor doctor, Patient patient, LocalDateTime date) {
        Consultation consultation = new Consultation(doctor,patient,date);
        em.persist(consultation);
        return consultation;
    }
}