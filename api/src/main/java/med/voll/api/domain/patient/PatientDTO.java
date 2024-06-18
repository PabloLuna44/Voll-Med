package med.voll.api.domain.patient;


public record PatientDTO(
        Long id,
        String name,
        String email
) {


    public PatientDTO(Patient patient){
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail()
        );

    }

}
