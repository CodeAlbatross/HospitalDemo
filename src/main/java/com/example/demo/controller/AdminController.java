package com.example.demo.controller;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import com.example.demo.entities.Sickroom;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;

/**
 * @author 刘知远
 */
@Controller
public class AdminController {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/doctors")
    public String list(Model model){
        Collection<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("docs",doctors);
        return "JDBCForAdmin/listforDoctor";
    }

    @GetMapping("/patients")
    public String patientList(Model model){
        Collection<Patient> patients = patientRepository.findAll();
        model.addAttribute("pats",patients);
        return "JDBCForAdmin/listforPatients";
    }

    /**
     * @param id 患者id
     * @return
     * 删除一对多的多端时，要解除一端对于多端的联系
     */
    @DeleteMapping("/patients/{id}")
    public String delete(@PathVariable("id") Integer id){
        System.out.println("删除患者"+patientRepository.findById(id));
        Patient patient = patientRepository.findById(id).get();
        int id1 = patient.getDoctor().getId();//找到医生的id

        Sickroom sickroom= patient.getSickroom();
        Doctor doctor=patient.getDoctor();

        doctor.getPatients().remove(patient);
        sickroom.getPatients().remove(patient);


        patientRepository.deleteById(id);

        return "redirect:/patients";
    }

    //转到新增医生页面
    @GetMapping("/addDoctor")
    public String add(Doctor doctor){
        return "JDBCForAdmin/addforDoctor";
    }

    //提交新增的医生
    @PostMapping("/submitdoc")
    public String submitdoc(Doctor doctor){
        doctorRepository.save(doctor);

        System.out.println("新增的员工"+doctor);
        return "redirect:/doctors";
    }



    //删除医生
    @DeleteMapping("/doc/{id}")
    public String deleteDoc(@PathVariable("id") Integer id){
        doctorRepository.deleteById(id);

        return "redirect:/doctors";
    }

    //跳转到更新相应id医生信息页面，并拉取相应id医生信息回显
    @GetMapping("/doc/{id}")
    public String updateDoc(@PathVariable("id") Integer id,Model model){
        Doctor doctor = doctorRepository.findById(id).orElse(null);

        model.addAttribute("doc",doctor);

        return "JDBCForAdmin/updateforDoctor";
    }

    //提交修改好的医生信息
    @PutMapping("/submitdoc")
    public String updatedoc(Doctor doctor){
        doctorRepository.save(doctor);
        System.out.println("修改的员工"+doctor);
        return "redirect:/doctors";
    }


    /**
     * 管理员信息维护
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/uploadadmin/{id}")
    public String uploadself(@PathVariable("id") Integer id,Model model){
        Admin admin = adminRepository.findById(id).orElse(null);
        model.addAttribute("admin",admin);
        return "/uploadforAdmin";
    }

    @PutMapping("/submitadmin")
    public String uploadAdmin(Admin admin,
                              HttpSession session){
        adminRepository.save(admin);
        session.setAttribute("loginuser",admin.getAdminName());
        System.out.println("修改成功"+admin);
        return "redirect:/uploadadmin/"+admin.getId();

    }
}
