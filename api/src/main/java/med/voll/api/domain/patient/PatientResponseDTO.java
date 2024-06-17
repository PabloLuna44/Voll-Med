package med.voll.api.domain.patient;

import med.voll.api.domain.address.DataAddress;

public record PatientResponseDTO(
        Long id,
        String name,
        String email,
        String phone,
        DataAddress address
) {
    public PatientResponseDTO(Patient patient){
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                new DataAddress(patient.getAddress())
        );
    }
}
