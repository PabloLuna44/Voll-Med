package med.voll.api.doctor;

import javax.print.Doc;

public record DoctorDTO(
        String name,
        Specialty specialty,
        String email,
        String document
) {


    public DoctorDTO(Doctor doctor){
        this(
                doctor.getName(),
                doctor.getSpecialty(),
                doctor.getEmail(),
                doctor.getDocument()
        );

    }

}
