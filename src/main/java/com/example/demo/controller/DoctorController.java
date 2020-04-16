package com.example.demo.controller;

import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class DoctorController {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/patients")
    public String list(Model model){
        Collection<Patient> patients = patientRepository.findAll();
        model.addAttribute("pats",patients);

        return "JDBCForDoctor/listforPatients";

    }

    @GetMapping("/addPatient")
    public String add(Model model){
        Collection<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors",doctors);
        return "JDBCForDoctor/addforPatient";
    }

    @PostMapping("/submitpat")
    public String submitpat(Patient patient){

        patientRepository.save(patient);
        return "redirect:/patients";

    }

}
