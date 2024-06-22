package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consultation.*;
import med.voll.api.infra.errors.IntegrityValidation;
import org.hibernate.integrator.internal.IntegratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/consultation")
@SecurityRequirement(name = "bearer-key")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping
    @Transactional
    @Operation(
            summary = "Register Consultation in DataBase",
            description="",
            tags = {"consult","post"}
    )
    public ResponseEntity<DetailConsultationDTO> save(@RequestBody @Valid ConsultationDTO parameter) throws IntegrityValidation {
        return ResponseEntity.ok(consultationService.save(parameter));
    }

    @DeleteMapping
    @Transactional
    @Operation(
            summary = "Delete Consultation in DataBase",
            description="",
            tags = {"consult","delete"}
    )
    public ResponseEntity<DetailConsultationDTO> delete(@RequestBody @Valid ConsultationCancelDTO parameter) throws IntegrityValidation{
        consultationService.delete(parameter);
        return ResponseEntity.noContent().build();
    }


}
