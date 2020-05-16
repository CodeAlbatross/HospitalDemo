package com.example.demo.controller;

import com.example.demo.entities.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    TblRepository tblRepository;

    @Autowired
    MedRepository medRepository;

    /**
     * 我的患者列表，获取医生id得到所属id患者信息
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/patients/{id}")
    public String list(Model model,
                       @PathVariable("id") Integer id){
        Collection<Patient> patients = patientRepository.findAllByDoctor(doctorRepository.findById(id).orElse(null));

        //在医生或者病房修改后，同步患者所属医生或病房名称
        Iterator<Patient> iterator = patients.iterator();
        while (iterator.hasNext()){
            Patient patient = iterator.next();
            patient.setPatDoctor(patient.getDoctor().getDocName());
            patient.setPatRoomName(patient.getSickroom().getRoomName());
            patientRepository.save(patient);
        }

        model.addAttribute("pats",patients);
        return "JDBCForDoctor/listforPatients";
    }

    /**
     * 跳转到添加界面
     * @param model
     * @param id 登录的人的id
     * @return
     */
    @GetMapping("/addPatient/{id}")
    public String add(Model model,
                      @PathVariable("id") Integer id){
        Collection<Doctor> doctors = doctorRepository.findAll();
        Collection<Sickroom> sickrooms = sickroomRepository.findAll();

        //判断病房是否为空
        sickrooms.removeIf(sickroom -> sickroom.getBedMaxNum() - sickroom.getOccupiedBed() == 0);

        model.addAttribute("doctors",doctors);
        model.addAttribute("rooms",sickrooms);
        model.addAttribute("loginId",id);

        return "JDBCForDoctor/addforPatient";
    }

    /**
     * 提交添加的患者
     * 记得把多对一的一端添加到多端的属性中
     * @param id 登陆的人的id
     * */
    @PostMapping("/submitpat/{id}")
    public String submitpat(Patient patient,
                            @PathVariable("id") Integer id,
                            TblCard tblCard){
        Doctor doctor = doctorRepository.findByDocName(patient.getPatDoctor());
        Sickroom sickroom = sickroomRepository.findByRoomName(patient.getPatRoomName());

        //增加病人时床位被占用数+1，床号以此排序
        sickroom.setOccupiedBed(sickroom.getOccupiedBed()+1);
        patient.setPatBedNum(sickroom.getOccupiedBed());

        patient.setDoctor(doctor);
        patient.setSickroom(sickroom);
        //patient.setTblCard(null);
        tblCard.setPatient(patient);

        //部分修改功能的bug，直接修改会导致id重复
        patient.setId(null);
        tblCard.setId(null);

        patientRepository.save(patient);
        System.out.println("添加病人"+patient);
        tblRepository.save(tblCard);
        sickroomRepository.save(sickroom);

        return "redirect:/patients/"+id;

    }

    /**
     * @param id 患者id
     * @return
     * 删除一对多的多端时，要解除一端对于多端的联系
     */
    @DeleteMapping("/patient/{id}")
    public String delete(@PathVariable("id") Integer id){
        System.out.println("删除患者"+patientRepository.findById(id));
        Patient patient = patientRepository.findById(id).get();
        int id1 = patient.getDoctor().getId();//找到医生的id

        Sickroom sickroom= patient.getSickroom();
        Doctor doctor=patient.getDoctor();

        //删除病人时床位被占用数-1，床号以此排序
        sickroom.setOccupiedBed(sickroom.getOccupiedBed()-1);

        doctor.getPatients().remove(patient);
        sickroom.getPatients().remove(patient);


        patientRepository.deleteById(id);
        sickroomRepository.save(sickroom);
        return "redirect:/patients/"+id1;
    }

    /**
     * 编辑患者信息
     * 拉取所有医生病房返回到页面
     * @param id 患者id
     * @param model
     * @return
     */
    @GetMapping("/patient/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model){
        Patient patient = patientRepository.findById(id).orElse(null);
        Collection<Doctor> doctors = doctorRepository.findAll();
        Collection<Sickroom> sickrooms = sickroomRepository.findAll();

        //判断病房是否为空
        sickrooms.removeIf(sickroom -> sickroom.getBedMaxNum() - sickroom.getOccupiedBed() == 0);

        model.addAttribute("patient",patient);
        model.addAttribute("doctors",doctors);
        model.addAttribute("rooms",sickrooms);
        return "JDBCForDoctor/updateforPatient";
    }

    /**
     * 记得把多对一的一端添加到多端的属性中
     * 提交更新后的患者信息
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
        return "redirect:/patients/"+doctor.getId();
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

    /**
     * 给患者添加药物和住院信息
     * @param id 患者id
     * @param model
     * @return
     */
    @GetMapping(value = "/cost/{id}")
    public String HospitalizationAndMedication(@PathVariable("id") Integer id,
                                               Model model){
        Collection<Medicine> medicines = medRepository.findAll();
        Patient patient = patientRepository.findById(id).orElse(null);
        model.addAttribute("meds",medicines);
        Collection<Medicine> medicines1 = patient.getMedicines();

        if (patient.getMedicines().isEmpty()){
            model.addAttribute("medsforpat",null);
        }else {
            model.addAttribute("medsforpat",medicines1);
        }

        model.addAttribute("id",id);

        return "/JDBCForDoctor/costforPatients";
    }


    /**
     * 把前端选中的药物添加到患者中
     * @param id
     * @param med
     * @return
     */
    @GetMapping(value = "/addMed/{id}")
    public String addMed(@PathVariable("id") Integer id,
                         @RequestParam("addmed") String med){
        Medicine medicine = medRepository.findByMedicineName(med);
        Patient patient = patientRepository.findById(id).orElse(null);
        patient.getMedicines().add(medicine);
        patientRepository.save(patient);
        return "redirect:/cost/"+id;
    }


}
