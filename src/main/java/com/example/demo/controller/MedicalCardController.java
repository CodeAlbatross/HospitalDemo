package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MedicalCardController {

    @GetMapping(value = "/MedicalCardForPatient/{id}")
    public String MedicalCardForPatient(@PathVariable("id") Integer id){
        return "JDBCForPatient/MedicalCard";

    }

    @GetMapping(value = "/MedicalCardForDoctor/{id}")
    public String MedicalCardForDoctor(@PathVariable("id") Integer id){
        return "JDBCForPatient/MedicalCard";

    }
}
