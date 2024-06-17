package med.voll.api.domain.patient;

import med.voll.api.domain.address.DataAddress;

public record PatientUpdateDTO(
        Long id,
        String name,
        DataAddress address
) {
    }

