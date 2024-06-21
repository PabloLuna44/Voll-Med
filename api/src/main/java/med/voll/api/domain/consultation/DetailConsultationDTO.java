package med.voll.api.domain.consultation;


import java.time.LocalDateTime;

public record DetailConsultationDTO(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime consultationDate
) {
}
