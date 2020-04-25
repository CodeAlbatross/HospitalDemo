package com.example.demo.controller;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    @PostMapping(value = "/login")
    public String LoginController(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  Map<String,Object> map,
                                  HttpSession session,
                                  Model model){
        Admin admin = adminRepository.findByAdminAccountAndAdminPsw(username,password);
        Doctor doctor = doctorRepository.findByLoginNumAndDocPsw(username,password);
        Patient patient = patientRepository.findByPatNameAndPatIdCardNum(username,password);
        //Patient patient = patientRepository
        //根据用户名密码在哪个数据库来决定登入哪个操作页面
        if (admin != null){
            session.setAttribute("log",username);
            session.setAttribute("loginuser",admin.getAdminName());
            System.out.println(username+":"+password);
            session.setAttribute("id",admin.getId());
            return "redirect:/AdminPage.html";
        }else if (doctor != null){
            session.setAttribute("log",username);
            session.setAttribute("loginuser",doctor.getDocName());
            System.out.println(username+":"+password);
            session.setAttribute("id",doctor.getId());
            return "redirect:/DoctorPage.html";
        }else if(patient!=null){
            session.setAttribute("log",username);
            session.setAttribute("loginuser",patient.getPatName());
            System.out.println(username+":"+password);
            session.setAttribute("id",patient.getId());
            model.addAttribute("loginId",patient.getId());
            return "redirect:/PatientPage.html";
        } else {
            map.put("msg","用户名密码错误");
            System.out.println("error");
            return "login";
        }

    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("log");
        return "login";
    }
}
