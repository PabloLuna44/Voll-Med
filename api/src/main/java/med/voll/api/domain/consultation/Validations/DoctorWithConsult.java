package med.voll.api.domain.consultation.Validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationDTO;
import med.voll.api.domain.consultation.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DoctorWithConsult implements ConsultValidator{

    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(ConsultationDTO consultationDTO){

        if(consultationDTO.doctorId()==null){
            return;
        }

        var doctorWithConsult=consultationRepository.existsByDoctorIdAndConsultationDateAndActiveTrue(consultationDTO.doctorId(),consultationDTO.consultationDate());

        if(doctorWithConsult){

            throw new ValidationException("Doctor has a consult in this date and time");

        }



    }
}
