package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consultation.Consultation;
import med.voll.api.domain.consultation.ConsultationDTO;
import med.voll.api.domain.consultation.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
