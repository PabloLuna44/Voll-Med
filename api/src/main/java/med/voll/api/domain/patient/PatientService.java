package med.voll.api.domain.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;



    public Patient save(DataPatient patient) {
       return patientRepository.save(new Patient(patient));
    }

    public Page<PatientDTO> findAll(Pageable pageable){
        return patientRepository.findByActiveTrue(pageable).map(PatientDTO::new);
    }

    public Patient find(Long id){
        return patientRepository.getReferenceById(id);
    }

     public Patient edit(PatientUpdateDTO patientUpdateDTO){
        Patient patient = patientRepository.getReferenceById(patientUpdateDTO.id());
        patient.update(patientUpdateDTO);

        return patient;
    }

    //SOFT DELETE
    public void delete(Long id){
        Patient patient = patientRepository.getReferenceById(id);
        patient.updateActive();
    }

// DELETE EN BASE DE DATOS
//    public void delete(Long id){
//        Patient patient = patientRepository.getReferenceById(id);
//        patientRepository.delete(patient);
//    }



}
