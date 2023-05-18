package com.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Employee_Table")
public class Employee {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 20, message = "Name must be between 3 to 20 characters")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name can only contain letters ")
	private String name;

	@NotBlank(message = "designation is required")
	@Size(min = 3, max = 20, message = "designation must be between 3 to 20 characters")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "designation can only contain letters ")
	private String designation;

	@NotBlank(message = "Salary is required")
	@Pattern(regexp = "^[0-9]+$", message = "Salary can only contain  numbers")
	private String salary;

	@NotBlank(message = "Phone Number is required")
	@Size(min = 10, message = "The value must be 10 digits")
	@Pattern(regexp = "^[0-9]+$", message = "PhoneNumber can only contain  numbers")
	private String phoneNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", salary=" + salary
				+ ", phoneNumber=" + phoneNumber + "]";
	}

}
