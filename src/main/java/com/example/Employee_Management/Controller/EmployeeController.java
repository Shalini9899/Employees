package com.example.Employee_Management.Controller;

import com.example.Employee_Management.Model.EmployeeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("employee", new EmployeeEntity());
        return "form";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") EmployeeEntity employee, Model model) {
        // Add the employee to the model to display on the index page
        model.addAttribute("employee", employee);
        return "index";
    }
    @GetMapping("/index")
    public String showIndex(Model model) {
        return "index";
    }
}
