package com.example.demo.controller;

import com.example.demo.entities.TblCard;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.TblRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MedicalCardController {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    TblRepository tblRepository;

    /**
     *
     * @param id 患者id
     * @param model
     * @return
     */
    @GetMapping(value = "/MedicalCardForPatient/{id}")
    public String MedicalCardForPatient(@PathVariable("id") Integer id,
                                        Model model){
        TblCard tblCard = tblRepository.findByPatient(patientRepository.findById(id).orElse(null));
        model.addAttribute("tbl",tblCard);

        return "JDBCForPatient/MedicalCard";

    }

    @GetMapping(value = "/MedicalCardForDoctor/{id}")
    public String MedicalCardForDoctor(@PathVariable("id") Integer id){
        return "JDBCForPatient/MedicalCard";

    }
}
