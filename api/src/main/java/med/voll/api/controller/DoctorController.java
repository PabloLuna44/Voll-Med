package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.DataDoctor;

import med.voll.api.doctor.DoctorDTO;
import med.voll.api.doctor.DoctorService;

import med.voll.api.doctor.DoctorUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping
    public void save(@RequestBody @Valid DataDoctor parameter){
        doctorService.save(parameter);
    }

    @GetMapping
    public Page<DoctorDTO> findAll(@PageableDefault(size=2) Pageable pageable){
        return doctorService.findAll(pageable);
    }

    @PutMapping
    @Transactional
    public void edit(@RequestBody @Valid DoctorUpdateDTO doctor){
        doctorService.edit(doctor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        doctorService.delete(id);
    }



}
