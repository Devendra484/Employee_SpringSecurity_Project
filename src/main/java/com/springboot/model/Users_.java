package com.springboot.model;

import com.springboot.validation.UniqueEmail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Users_table")
public class Users_ {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 20, message = "Username must be between 3 to 20 characters")
	@Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Username can only contain letters and numbers")
	private String userName;

	@Email(message = "Ivalid Email")
	// @UniqueEmail(message="email already exists")
	@NotBlank(message = "Email is required")
	private String email;

	@NotBlank(message = "Phone Number is required")
	@Size(min = 10, message = "The value must be 10 digits")
	@Pattern(regexp = "^[0-9]+$", message = "PhoneNumber can only contain  numbers")
	private String phoneNumber;

	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be 8 characters")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}", message = "Password must be combination of Upper Case ,Lower Case, Numbers and Special characters")
	private String password;

	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", userName=" + userName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", password=" + password + ", role=" + role + "]";
	}

}
