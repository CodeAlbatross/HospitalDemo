package com.example.demo.controller;

import com.example.demo.entities.Sickroom;
import com.example.demo.repository.SickroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class SickroomController {

    @Autowired
    SickroomRepository sickroomRepository;

    @GetMapping("/sickrooms/{id}")
    public String roomlist(Model model,
                           @PathVariable("id") Integer id,
                           String department){
        //Collection<Sickroom> sickrooms = sickroomRepository.findAll();
        //根据科室将病房传回前端
        Collection<Sickroom> sickrooms = sickroomRepository.findAllByRoomDepartment(department);
        model.addAttribute("rooms",sickrooms);

        if(id==0){
        return "JDBCForSickroom/listforSickroom";
        }else if (id==1){
            return "JDBCForSickroom/listforSickroomDoc";
        }else {
            return "";
        }
    }

    @GetMapping("/addSickroom")
    public String addroom(){
        return "JDBCForSickroom/addforSickroom";
    }

    @PostMapping("/submitroom")
    public String submitroom(Sickroom sickroom){
        sickroomRepository.save(sickroom);
        System.out.println("新增的病房"+sickroom);
        return "redirect:/sickrooms/0";
    }

    @DeleteMapping("/room/{id}")
    public String deleteroom(@PathVariable("id") Integer id){
        sickroomRepository.deleteById(id);
        return "redirect:/sickrooms/0";
    }

    //跳转到更新相应id病房信息页面，并拉取相应id病房信息回显
    @GetMapping("/room/{id}")
    public String updateroom(@PathVariable("id") Integer id,Model model){
        Sickroom sickroom = sickroomRepository.findById(id).orElse(null);
        model.addAttribute("room",sickroom);
        return "JDBCForSickroom/updateforSickroom";
    }

    @PutMapping("/submitroom")
    public String update(Sickroom sickroom){
        sickroomRepository.save(sickroom);
        System.out.println("修改的病房"+sickroom);
        return "redirect:/sickrooms/0";
    }
}
