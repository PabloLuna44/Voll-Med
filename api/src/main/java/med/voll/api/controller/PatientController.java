package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import med.voll.api.domain.patient.DataPatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> save(@RequestBody @Valid DataPatient parameter, UriComponentsBuilder uriComponentsBuilder){
        Patient patient=patientService.save(parameter);
        PatientResponseDTO patientResponseDTO=new PatientResponseDTO(patient);

        URI url=uriComponentsBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(url).body(patientResponseDTO);
    }

      @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> find(@PathVariable Long id){
        Patient patient=patientService.find(id);
        PatientResponseDTO patientResponseDTO=new PatientResponseDTO(patient);

        return ResponseEntity.ok(patientResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<PatientDTO>> findAll(@PageableDefault(size=2) Pageable pageable){
        return ResponseEntity.ok(patientService.findAll(pageable));
    }

    @PutMapping
    @Transactional
    public ResponseEntity edit(@RequestBody @Valid PatientUpdateDTO doctor){

        return ResponseEntity.ok(new PatientResponseDTO(patientService.edit(doctor)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        patientService.delete(id);

        return ResponseEntity.noContent().build();
    }



}
