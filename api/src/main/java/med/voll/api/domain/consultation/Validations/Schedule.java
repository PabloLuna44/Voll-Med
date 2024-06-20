package med.voll.api.domain.consultation.Validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class Schedule implements ConsultValidator{

    public void validate(ConsultationDTO consultationDTO){

        var sunday= DayOfWeek.SUNDAY.equals(consultationDTO.consultationDate().getDayOfWeek());

        var time= consultationDTO.consultationDate().getHour();


        if(sunday || time<7 || time>19){
            throw new ValidationException("Schedule of attention is monday to saturday of 7:00 at 19:00");
        }
    }


}
