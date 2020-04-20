package com.example.demo.controller;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.entities.Sickroom;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.SickroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

    @GetMapping("/patients/{id}")
    public String list(Model model,
                       @PathVariable("id") Integer id){
        Collection<Patient> patients = patientRepository.findAllByDoctor(doctorRepository.findById(id).orElse(null));

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
     * 记得把多对一的一端添加到多端的属性中
     * */
    @PostMapping("/submitpat/{id}")
    public String submitpat(Patient patient,
                            @PathVariable("id") Integer id){
        Doctor doctor = doctorRepository.findByDocName(patient.getPatDoctor());
        Sickroom sickroom = sickroomRepository.findByRoomName(patient.getPatRoomName());

        patient.setDoctor(doctor);
        patient.setSickroom(sickroom);
        patientRepository.save(patient);

        return "redirect:/patients/"+id;

    }

    /**
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

    /**
     * 拉取所有医生病房返回到页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/patient/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model){
        Patient patient = patientRepository.findById(id).orElse(null);
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

        model.addAttribute("patient",patient);
        model.addAttribute("doctors",doctors);
        model.addAttribute("rooms",sickrooms);
        return "JDBCForDoctor/updateforPatient";
    }

    /**
     * 记得把多对一的一端添加到多端的属性中
     * @param patient
     * @return
     */
    @PutMapping("/updatepat")
    public String submit(Patient patient){
        Doctor doctor = doctorRepository.findByDocName(patient.getPatDoctor());
        Sickroom sickroom = sickroomRepository.findByRoomName(patient.getPatRoomName());
        patient.setDoctor(doctor);
        patient.setSickroom(sickroom);
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/updatedoctor/{id}")
    public String uploadself(@PathVariable("id") Integer id,Model model){
        Doctor doctor = doctorRepository.findById(id).orElse(null);
        model.addAttribute("doctor",doctor);
        return "/JDBCForDoctor/updateforDoctor2";
    }

    @PutMapping("/submitdoctor")
    public String uploadAdmin(Doctor doctor,
                              HttpSession session){
        doctorRepository.save(doctor);
        session.setAttribute("loginuser",doctor.getDocName());
        System.out.println("修改成功"+doctor);
        return "redirect:/updatedoctor/"+doctor.getId();

    }

}
