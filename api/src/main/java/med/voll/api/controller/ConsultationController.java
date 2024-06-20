package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.consultation.ConsultationCancelDTO;
import med.voll.api.domain.consultation.ConsultationDTO;
import med.voll.api.domain.consultation.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping
    @Transactional
    public ResponseEntity<Consultation> save(@RequestBody @Valid ConsultationDTO parameter){

        return ResponseEntity.ok(consultationService.save(parameter));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Consultation> delete(@RequestBody @Valid ConsultationCancelDTO parameter){
        consultationService.delete(parameter);
        return ResponseEntity.noContent().build();
    }


}
