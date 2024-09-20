package com.emp.controller;

import com.emp.entity.Employee;
import com.emp.service.EmpService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EmpService empService;

    @GetMapping("/")
    public String index(Model m){
        List<Employee> list =empService.getAllEmp();
        m.addAttribute("empList",list);
        return "index";
    }

    @GetMapping("/loademp")
    public String loadEmpSave(){
        return "emp_save";
    }

    @GetMapping("/editemp/{id}")
    public String EditEmp(@PathVariable int id,Model m){
        Employee emp=empService.getEmpById(id);
        m.addAttribute("emp",emp);
        return "emp_edit";
    }

    @PostMapping("/saveemp")
    public String saveEmp(@ModelAttribute Employee emp, HttpSession session)
    {
        //System.out.println(emp);
        Employee newEmp =empService.saveEmp(emp);

        if(newEmp!=null){
            session.setAttribute("msg","Register Successfully");
        }else{
            session.setAttribute("msg","Something went wrong");
        }

        return "redirect:/loademp";
    }

    @PostMapping("/updateemp")
    public String updateEmp(@ModelAttribute Employee emp, HttpSession session)
    {
        //System.out.println(emp);
        Employee updateEmp =empService.saveEmp(emp);

        if(updateEmp!=null){
            session.setAttribute("msg","Updated Successfully");
        }else{
            session.setAttribute("msg","Something went wrong");
        }

        return "redirect:/";
    }

    @GetMapping("/deleteemp/{id}")
    public String deleteEmp(@PathVariable int id,HttpSession session){
        boolean f= empService.deleteEmp(id);
        if(f){
            session.setAttribute("msg","Deleted Successfully");
        }else{
            session.setAttribute("msg","Something Went Wrong");
        }
        return "redirect:/";
    }
}
