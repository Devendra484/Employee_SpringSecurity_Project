package com.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Repository.EmployeeRepository;
import com.springboot.model.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@PersistenceContext
	private EntityManager entityManager;

	// get all employee Details
	public List<Employee> getEmployeDetails() {
		List<Employee> employee_List = employeeRepository.findAll();
		if (employee_List.isEmpty()) {
			return null;
		} else {
			return employee_List;
		}

	}

	// delete employeeById
	@Transactional
	public void deleteEmployeeById(Long id) {
		Query query = entityManager.createQuery("SELECT MAX(e.id) FROM Employee e");
		Long maxId = (Long) query.getSingleResult();
		employeeRepository.deleteById(id);
		employeeRepository.updateIds(id, maxId);

	}

	// find By Id
	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).get();
	}

	// updateEmployee
	public Employee updateEmployee(Employee employee, Long id) {

		Employee employee_ = getEmployeeById(id);

		employee_.setId(employee.getId());
		employee_.setName(employee.getName());
		employee_.setDesignation(employee.getDesignation());
		employee_.setSalary(employee.getSalary());
		employee_.setPhoneNumber(employee.getPhoneNumber());

		return employeeRepository.save(employee_);
	}

	public Object addEmployee(Employee employee) {
		Query query = entityManager.createQuery("SELECT MAX(e.id) FROM Employee e");
		Long maxId = (Long) query.getSingleResult();
		if (maxId == null) {
			maxId = 0L;
		}
		employee.setId(maxId + 1);
		System.out.println(employee.getId());
		return employeeRepository.save(employee);

	}

}
