package med.voll.api.domain.consultation.Validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ScheduleAnticipation implements ConsultValidator{

    public void validate(ConsultationDTO consultationDTO){

        var now= LocalDateTime.now();
        var date=consultationDTO.consultationDate();

        var validate= Duration.between(now,date).toMinutes()<30;


        if(validate){
            throw new ValidationException("Consult has to be scheduled with 30 minutes in advance");
        }

    }
}
