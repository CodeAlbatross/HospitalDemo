package com.example.demo.controller;

import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/addPatient")
    public String add(){
        return "JDBCForDoctor/addforPatient.html";
    }
}
