package com.example.demo.controller;

import com.example.demo.base.myDate;
import com.example.demo.entities.*;
import com.example.demo.repository.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Controller
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    SickroomRepository sickroomRepository;

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatMedRepository patMedRepository;

    @Autowired
    costListRepository costListRepository;



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

    /**
     * 费用清单
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/mycost/{id}")
    public String cost(@PathVariable("id") Integer id,
                       Model model) throws ParseException {

        //找到患者
        Patient patient = patientRepository.findById(id).orElse(null);
        Collection<Medicine> medicines1 = patient.getMedicines();

        if (patient.getMedicines().isEmpty()){
            model.addAttribute("medsforpat",null);
        }else {
            model.addAttribute("medsforpat",medicines1);
        }

        //找到患者的药物清单
        Collection<PatMed> medicines2 = patMedRepository.findAllByPatient(patient);
        Collection<medlist> medlists = new ArrayList<>();
        for (PatMed patMed : medicines2) {
            String patname = patMed.getPatient().getPatName();
            String medname = patMed.getMedicine().getMedicineName();
            String costdata = patMed.getCostData();
            String medunit = patMed.getMedicine().getMedicineUnit();
            int count = patMed.getCount();
            float medcost = patMed.getMedicine().getMedicineCost();
            medlist medlist1 = new medlist();
            medlist1.setCount(count);
            medlist1.setMedname(medname);
            medlist1.setPatname(patname);
            medlist1.setMedcost(medcost);
            medlist1.setCostData(costdata);
            medlist1.setMedunit(medunit);
            medlists.add(medlist1);
        }
        model.addAttribute("medlist",medlists);


        float totalCost=0;
        //计算药费
        for (PatMed patMed : medicines2) {
            totalCost += patMed.getCount()*patMed.getMedicine().getMedicineCost();
        }
        //计算天数
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        myDate myDate = new myDate();
        myDate.setBeginDate(patient.getBirth());
        myDate.setFinDate(df.format(new Date()));

        totalCost+=myDate.num()*patient.getSickroom().getRoomCost();

        model.addAttribute("daynum",myDate.num());//住院天数
        model.addAttribute("roomcost",myDate.num()*patient.getSickroom().getRoomCost());//床费

        //计算费用清单中的费用
        Collection<costList> costLists = costListRepository.findAllByPatient(patient);
        Iterator<costList> iterator = costLists.iterator();
        while (iterator.hasNext()){
            costList costList = iterator.next();
            System.out.println(costList.totalCost());
            totalCost+=costList.totalCost();
        }
        model.addAttribute("costlists",costLists);
        patient.setTotalCost(totalCost);

        model.addAttribute("totalcost",totalCost);//总费用
        patientRepository.save(patient);
        return "/JDBCForPatient/costforPatients";
    }

}
