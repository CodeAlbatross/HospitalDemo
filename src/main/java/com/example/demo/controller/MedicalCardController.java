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
}
