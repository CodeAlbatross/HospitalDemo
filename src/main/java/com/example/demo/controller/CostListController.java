package com.example.demo.controller;

import com.example.demo.entities.Patient;
import com.example.demo.entities.costList;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.costListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.xml.ws.soap.Addressing;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
public class CostListController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    costListRepository costListRepository;

    /**
     * 转到患者消费清单界面
     * @param id
     * @return
     */
    @GetMapping("/addcostlist/{id}")
    public String costlist(@PathVariable("id") Integer id,
                           Model model){
        Patient patient = patientRepository.findById(id).orElse(null);
        Collection<costList> costLists = costListRepository.findAllByPatient(patient);
        model.addAttribute("costlists",costLists);
        return "/JDBCForDoctor/costlistforPatients";
    }

    /**
     * 提交患者费用清单
     * @param id
     * @param costList
     * @return
     */
    @PostMapping("/submitcostlist/{id}")
    public String addCostList(@PathVariable("id") Integer id,
                              costList costList){
        Patient patient = patientRepository.findById(id).orElse(null);
        costList.setPatient(patient);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        costList.setCostData(df.format(new Date()));
        costList.setId(null);
        costListRepository.save(costList);
        return "redirect:/addcostlist/"+id;
    }

    @DeleteMapping("/deletecostlist/{id}")
    public String deleteCostList(@PathVariable("id") Integer id){
        costList costList = costListRepository.findById(id).orElse(null);
        Patient patient = costList.getPatient();
        patient.getCostLists().remove(costList);
        costListRepository.delete(costList);
        patientRepository.save(patient);
        return "redirect:/addcostlist/"+patient.getId();
    }
}
