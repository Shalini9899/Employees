package com.example.Employee_Management.Controller.Thymleaf;

import com.example.Employee_Management.Model.EmployeeEntity;
import com.example.Employee_Management.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employees")
public class ThymeleafController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String getAllEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new EmployeeEntity());
        return "form";
    }


    @PostMapping("/new")
    public String saveNewEmployee(@ModelAttribute EmployeeEntity employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable Long id, Model model) {
        employeeService.getEmployeeById(id).ifPresent(employee -> {
            model.addAttribute("employee", employee);
            model.addAttribute("isUpdate", true);
        });
        return "form";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, EmployeeEntity employeeDetails) {
        employeeService.updateEmployee(id, employeeDetails);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }

    @GetMapping("/view/{id}")
    public String viewEmployee(@PathVariable Long id, Model model) {
        employeeService.getEmployeeById(id).ifPresent(employee -> model.addAttribute("employee", employee));
        return "view";
    }

}
