package med.voll.api.domain.doctor;



public record DoctorDTO(
        Long id,
        String name,
        Specialty specialty,
        String email,
        String document
) {


    public DoctorDTO(Doctor doctor){
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialty(),
                doctor.getEmail(),
                doctor.getDocument()
        );

    }

}
