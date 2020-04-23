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
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;

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

    /**
     * 查询我的病房信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/mySickroom/{id}")
    public String mySickroom(@PathVariable("id") Integer id,
                             Model model){
        Patient patient = patientRepository.findById(id).orElse(null);
        Sickroom sickroom = patient.getSickroom();
        model.addAttribute("myroom",sickroom);
        model.addAttribute("patient",patient);
        return "JDBCForPatient/listforMySickroom";
    }

    /**
     * 查询所有医生
     */
    @GetMapping(value = "/doctorsforpatient")
    public String doctors(Model model){
        Collection<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors",doctors);
        return "JDBCForPatient/listforDoctor";
    }

    /**
     * 查询所有病房
     * @param model
     * @return
     */
    @GetMapping(value = "/sickroomsforpatient")
    public String sickrooms(Model model){
        Collection<Sickroom> sickrooms = sickroomRepository.findAll();
        model.addAttribute("rooms",sickrooms);
        return "JDBCForPatient/listforSickroom";
    }

    @GetMapping(value = "/updatepatient/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model){
        Patient patient = patientRepository.findById(id).orElse(null);
        model.addAttribute("patient",patient);
        return "JDBCForPatient/updateforPatient";
    }

    /**
     * 更新多对一的多端时，要记得把对应的一端保存
     * @param patient
     * @param session
     * @return
     */
    @PutMapping(value = "/submitpatient")
    public String submit(Patient patient,
                         HttpSession session){


        patientRepository.save(patient);
        session.setAttribute("loginuser",patient.getPatName());
        return "redirect:/updatepatient/"+patient.getId();
    }

}
