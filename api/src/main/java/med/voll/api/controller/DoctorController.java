package med.voll.api.controller;

import jakarta.validation.Valid;

import med.voll.api.domain.doctor.*;
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
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> save(@RequestBody @Valid DataDoctor parameter, UriComponentsBuilder uriComponentsBuilder){
        Doctor doctor=doctorService.save(parameter);
        DoctorResponseDTO doctorResponseDTO=new DoctorResponseDTO(doctor);

        URI url=uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(url).body(doctorResponseDTO);
    }

      @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> find(@PathVariable Long id){
        Doctor doctor=doctorService.find(id);
        DoctorResponseDTO doctorResponseDTO=new DoctorResponseDTO(doctor);

        return ResponseEntity.ok(doctorResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DoctorDTO>> findAll(@PageableDefault(size=2) Pageable pageable){
        return ResponseEntity.ok(doctorService.findAll(pageable));
    }

    @PutMapping
    @Transactional
    public ResponseEntity edit(@RequestBody @Valid DoctorUpdateDTO doctor){

        return ResponseEntity.ok(new DoctorResponseDTO(doctorService.edit(doctor)));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        doctorService.delete(id);

        return ResponseEntity.noContent().build();
    }



}
