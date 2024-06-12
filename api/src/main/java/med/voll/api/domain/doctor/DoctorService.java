package med.voll.api.domain.doctor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;



    public Doctor save(DataDoctor doctor) {
       return doctorRepository.save(new Doctor(doctor));
    }

    public Page<DoctorDTO> findAll(Pageable pageable){
        return doctorRepository.findByActiveTrue(pageable).map(DoctorDTO::new);
    }

    public Doctor find(Long id){
        return doctorRepository.getReferenceById(id);
    }

     public Doctor edit(DoctorUpdateDTO doctorUpdateDTO){
        Doctor doctor =doctorRepository.getReferenceById(doctorUpdateDTO.id());
        doctor.update(doctorUpdateDTO);

        return doctor;
    }

    //SOFT DELETE
    public void delete(Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.updateActive();
    }

// DELETE EN BASE DE DATOS
//    public void delete(Long id){
//        Doctor doctor = doctorRepository.getReferenceById(id);
//        doctorRepository.delete(doctor);
//    }



}
