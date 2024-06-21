package med.voll.api.domain.consultation;



import jakarta.validation.ValidationException;
import med.voll.api.domain.consultation.DeleteValidations.DeleteConsultValidator;
import med.voll.api.domain.consultation.Validations.ConsultValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    List<ConsultValidator> validations;

    @Autowired
    List<DeleteConsultValidator> deleteValidations;


    public DetailConsultationDTO save(ConsultationDTO consultationDTO){
        Optional<Patient> patient=patientRepository.findByIdAndActiveIsTrue(consultationDTO.patientId());
        Optional<Doctor> doctor = doctorRepository.findByIdAndActiveIsTrue(consultationDTO.doctorId());

        if (patient.isEmpty()){
            throw new IntegrityValidation("Patient Not Found ");
        }

        if (consultationDTO.doctorId()!=null && doctor.isEmpty() ){
            throw new IntegrityValidation("Doctor Not Found ");
        }

        //Validadores
        doctor=selectDoctor(consultationDTO);
        var consult=new ConsultationDTO(null,doctor.get().getId(), consultationDTO.specialty(),consultationDTO.patientId(),consultationDTO.consultationDate());

        validations.forEach(v-> v.validate(consult));

        Consultation consultation=new Consultation(doctor.get(),patient.get(),consult.consultationDate());
        consultationRepository.save(consultation);

        DetailConsultationDTO detailConsultationDTO=new DetailConsultationDTO(consultation.getId(),consultation.getDoctor().getId(),consultation.getPatient().getId(),consultation.getConsultationDate());
        return detailConsultationDTO;
    }


    public void delete(ConsultationCancelDTO consultationCancelDTO){

        deleteValidations.forEach(v -> v.validate(consultationCancelDTO));
        var consultation=consultationRepository.getReferenceById(consultationCancelDTO.id());
        consultation.cancel(false,consultationCancelDTO.reason());

    }


//    public void delete(Long id){
//        deleteValidations.forEach(v -> v.validate(id));
//        var consultation=consultationRepository.findById(id);
//        consultationRepository.delete(consultation.get());
//    }


    private Optional<Doctor> selectDoctor(ConsultationDTO consultationDTO){

        if(consultationDTO.doctorId()!=null){
            return doctorRepository.findByIdAndActiveIsTrue(consultationDTO.doctorId());
        }
        if(consultationDTO.specialty()==null){
            throw new IntegrityValidation("You Should enter a special doctor");
        }

        Optional<Doctor> doctor=doctorRepository.findDoctorWithSpecialtyInDate(consultationDTO.specialty(),consultationDTO.consultationDate());

        if(doctor.isEmpty()){
            throw new ValidationException("Doctor with "+consultationDTO.specialty().toString().toLowerCase()+" specialty not found");
        }

        return doctor;


    }




}
