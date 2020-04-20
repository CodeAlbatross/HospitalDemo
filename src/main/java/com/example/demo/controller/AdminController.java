package com.example.demo.controller;

import com.example.demo.entities.Admin;
import com.example.demo.entities.Doctor;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.DoctorRepository;
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

    @GetMapping("/doctors")
    public String list(Model model){
        Collection<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("docs",doctors);
        return "JDBCForAdmin/listforDoctor";
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
