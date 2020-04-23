package com.example.demo.controller;

import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.entities.Sickroom;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.SickroomRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    SickroomRepository sickroomRepository;

    @Autowired
    DoctorRepository doctorRepository;


    /**
     * 查询我的医生
     * @param id
     * @return
     */
    @GetMapping(value = "/myDoctor/{id}")
    public String myDoctor(@PathVariable("id") Integer id,
                           Model model){
        Patient patient = patientRepository.findById(id).orElse(null);
        Doctor doctor = patient.getDoctor();
        model.addAttribute("mydoc",doctor);
        return "JDBCForPatient/listforMyDoctor";
    }

    @GetMapping(value = "/mySickroom/{id}")
    public String mySickroom(@PathVariable("id") Integer id,
                             Model model){
        Patient patient = patientRepository.findById(id).orElse(null);
        Sickroom sickroom = patient.getSickroom();
        model.addAttribute("myroom",sickroom);
        model.addAttribute("patient",patient);
        return "JDBCForPatient/listforMySickroom";
    }

}
