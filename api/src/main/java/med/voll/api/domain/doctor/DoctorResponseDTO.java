package med.voll.api.domain.doctor;

import med.voll.api.domain.address.DataAddress;

public record DoctorResponseDTO(
        Long id,
        String name,
        String email,
        String phone,
        String document,
        DataAddress address
) {
    public DoctorResponseDTO(Doctor doctor){
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getPhone(),
                doctor.getDocument(),
                new DataAddress(doctor.getAddress())
        );
    }
}
