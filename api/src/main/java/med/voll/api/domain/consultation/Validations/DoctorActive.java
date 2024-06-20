package med.voll.api.domain.consultation.Validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.ConsultationDTO;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DoctorActive implements ConsultValidator{

    @Autowired
    private DoctorRepository doctorRepository;


    public void validate(ConsultationDTO consultationDTO){

        Optional<Doctor> doctor=doctorRepository.findByIdAndActiveIsTrue(consultationDTO.doctorId());

        if(doctor.isEmpty()){
            throw new ValidationException("Doctor not found or Doctor is inactive");
        }

    }


}
