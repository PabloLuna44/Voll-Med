package med.voll.api.domain.consultation.Validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationDTO;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PatientActive implements ConsultValidator{


    @Autowired
    private PatientRepository patientRepository;

    public void validate(ConsultationDTO consultationDTO){

        Optional<Patient> patient=patientRepository.findByIdAndActiveIsTrue(consultationDTO.patientId());

        if(patient.isEmpty()){
            throw new ValidationException("Not found Patient or Patient is Inactive");
        }


    }


}
