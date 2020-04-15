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

    @GetMapping("/sickrooms")
    public String roomlist(Model model){
        Collection<Sickroom> sickrooms = sickroomRepository.findAll();
        model.addAttribute("rooms",sickrooms);
        return "JDBCForSickroom/listforSickroom";
    }

    @GetMapping("/addSickroom")
    public String addroom(){
        return "JDBCForSickroom/addforSickroom";
    }

    @PostMapping("/submitroom")
    public String submitroom(Sickroom sickroom){
        sickroomRepository.save(sickroom);
        System.out.println("新增的病房"+sickroom);
        return "redirect:/sickrooms";
    }

    @DeleteMapping("/room/{id}")
    public String deleteroom(@PathVariable("id") Integer id){
        sickroomRepository.deleteById(id);
        return "redirect:/sickrooms";
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
        return "redirect:/sickrooms";
    }
}
