package med.voll.api.domain.doctor;

import med.voll.api.domain.address.DataAddress;

public record DoctorUpdateDTO(
        Long id,
        String name,
        String document,
        DataAddress address
) {
    }

