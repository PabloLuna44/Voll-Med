package med.voll.api.domain.consultation.Validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationDTO;
import med.voll.api.domain.consultation.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientWithoutConsult implements ConsultValidator{

    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(ConsultationDTO consultationDTO){

        var openHour=consultationDTO.consultationDate().withHour(7);
        var closeHour=consultationDTO.consultationDate().withHour(18);

        var patient=consultationRepository.existsByPatientIdAndConsultationDateBetween(consultationDTO.patientId(),openHour,closeHour);

        if(patient){
            throw new ValidationException("Do not allow consultation with the same patient on the same day.");
        }


    }

}
