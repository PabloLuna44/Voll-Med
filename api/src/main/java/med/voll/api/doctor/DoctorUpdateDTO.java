package med.voll.api.doctor;

import med.voll.api.address.DataAddress;

public record DoctorUpdateDTO(
        Long id,
        String name,
        String document,
        DataAddress address
) {
}
