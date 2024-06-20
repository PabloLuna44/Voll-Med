package med.voll.api.domain.consultation.DeleteValidations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.consultation.ConsultationCancelDTO;
import med.voll.api.domain.consultation.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExistConsult implements DeleteConsultValidator{

    @Autowired
    private ConsultationRepository consultationRepository;

    public void validate(ConsultationCancelDTO consultationCancelDTO){

        Optional<Consultation> consultation=consultationRepository.findById(consultationCancelDTO.id());

        if(consultation.isEmpty()){
            throw new ValidationException("Id of consultation not found");
        }

    }
}
