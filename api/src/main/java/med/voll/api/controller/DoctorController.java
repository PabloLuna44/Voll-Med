package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.DataDoctor;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorDTO;
import med.voll.api.doctor.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<DoctorDTO> findAll(Pageable pageable){
        return doctorService.findAll(pageable);
    }


}
