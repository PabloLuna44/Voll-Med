package med.voll.api.domain.consultation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record ConsultationDTO(
        Long id,
        Long doctorId,
        Specialty specialty,
        @Positive
        @NotNull
        Long patientId,
        @Future
        @NotNull
        LocalDateTime consultationDate
) {

}
