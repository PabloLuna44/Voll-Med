package med.voll.api.domain.consultation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsultationCancelDTO(
        @NotNull
        Long id,
        @NotNull
        Reason reason
) {
}
