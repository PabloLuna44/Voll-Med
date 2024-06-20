package med.voll.api.domain.consultation;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;


public interface ConsultationRepository extends JpaRepository<Consultation,Long> {


    Boolean existsByPatientIdAndConsultationDateBetweenAndActiveTrue(Long aLong, LocalDateTime openHour, LocalDateTime closeHour);

    Boolean existsByDoctorIdAndConsultationDateAndActiveTrue(Long aLong, LocalDateTime localDateTime);

}
