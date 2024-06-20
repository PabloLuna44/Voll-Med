package med.voll.api.domain.consultation.Validations;

import med.voll.api.domain.consultation.ConsultationDTO;

public interface ConsultValidator {
    void validate(ConsultationDTO consultationDTO);
}
