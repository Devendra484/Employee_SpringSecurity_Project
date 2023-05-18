package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springboot.model.Employee;
import com.springboot.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/admin")
	public String getHome() {
		System.out.println("hello");
		return "Home_A";

	}

	@GetMapping("/user")
	public String getHome_u() {
		System.out.println("hello");
		return "Home_U";

	}
	// @GetMapping("/newPage")
	// public String newm(Model model){
	// return "Home";
	// }

	@GetMapping("/employees")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String getEmployees(Model model) {
		model.addAttribute("employees", employeeService.getEmployeDetails());
		return "Employees";
	}

	@GetMapping("/employees_List")
	@PreAuthorize("permitAll")
	public String getEmployees_List(Model model) {
		model.addAttribute("employees", employeeService.getEmployeDetails());
		return "Employees_U";
	}

	@GetMapping("/employees/{id}")
	public String getEmployeesById(@PathVariable("id") Long id, Model model) {
		model.addAttribute("employees", employeeService.getEmployeDetails());
		return "Employees";
	}

	@RequestMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String delEmployees(@PathVariable("id") Long id, Model model) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/employees";

	}

	@GetMapping("/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editEmployee(@PathVariable Long id, Model model) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "Edit";

	}

	@RequestMapping("/update/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String updateEmployee(@PathVariable Long id, @Valid @ModelAttribute("employee") Employee employee,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "Edit";
		}
		employeeService.updateEmployee(employee, id);
		return "redirect:/employees";
	}

	@RequestMapping("/createEmployee")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String createEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("newEmployee", employee);
		return "Add";
	}

	@RequestMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addEmployee(@Valid @ModelAttribute("newEmployee") Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "Add";
		}
		employeeService.addEmployee(employee);
		return "redirect:/employees";

	}

}
