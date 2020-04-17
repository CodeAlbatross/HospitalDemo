package com.example.demo.controller;

import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.entities.Sickroom;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.SickroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author lzyyy
 */
@Controller
public class DoctorController {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    SickroomRepository sickroomRepository;

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/patients")
    public String list(Model model){
        Collection<Patient> patients = patientRepository.findAll();
        model.addAttribute("pats",patients);

        return "JDBCForDoctor/listforPatients";

    }

    /**
     * 跳转到添加界面
     * */
    @GetMapping("/addPatient")
    public String add(Model model){
        Collection<Doctor> doctors = doctorRepository.findAll();
        Collection<Sickroom> sickrooms = sickroomRepository.findAll();
        Iterator<Sickroom> it = sickrooms.iterator();

        //判断病房是否为空
        while (it.hasNext()){
            Sickroom sickroom=it.next();

            if(sickroom.getBedMaxNum()-sickroom.getOccupiedBed() == 0){

                it.remove();
            }
        }

        model.addAttribute("doctors",doctors);
        model.addAttribute("rooms",sickrooms);
        return "JDBCForDoctor/addforPatient";
    }

    /**
     * 提交添加的医生
     * */
    @PostMapping("/submitpat")
    public String submitpat(Patient patient){
        Doctor doctor = doctorRepository.findByDocName(patient.getPatDoctor());
        Sickroom sickroom = sickroomRepository.findByRoomName(patient.getPatRoomName());

        patient.setDoctor(doctor);
        patient.setSickroom(sickroom);
        patientRepository.save(patient);

        return "redirect:/patients";

    }

    /**
     *
     * @param id
     * @return
     * 删除一对多的多端时，要解除一端对于多端的联系
     */
    @DeleteMapping("/patient/{id}")
    public String delete(@PathVariable("id") Integer id){
        System.out.println("删除患者"+patientRepository.findById(id));
        Patient patient = patientRepository.findById(id).get();

        Sickroom sickroom= patient.getSickroom();
        Doctor doctor=patient.getDoctor();

        doctor.getPatients().remove(patient);
        sickroom.getPatients().remove(patient);

        patientRepository.deleteById(id);

        return "redirect:/patients";
    }

}
