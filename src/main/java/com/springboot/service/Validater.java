package com.springboot.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.springboot.model.Users_;

@Service
public class Validater {

	static String nameRegex = "^[a-zA-Z._-]{3,15}$";
	static String emailRegex = "^[a-zA-Z0-9@.]+$";
	static String numberRegex = "^[0-9]{10}$";
	static String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";

	static Pattern namePattern = Pattern.compile(nameRegex);
	static Pattern emailPattern = Pattern.compile(emailRegex);
	static Pattern passwordPattern = Pattern.compile(passwordRegex);
	static Pattern numberPattern = Pattern.compile(numberRegex);

	public static String allvalidations(Users_ users) {
		Matcher nameMatcher = namePattern.matcher(users.getUserName());
		Matcher emailMatcher = emailPattern.matcher(users.getEmail());
		Matcher passwordMatcher = passwordPattern.matcher(users.getPassword());
		Matcher numberMatcher = numberPattern.matcher(users.getPhoneNumber().toString());

		if (!nameMatcher.matches()) {

			return "Username is not valid, it should contain only letters and between 3 to 15 characters long";

		} else if (!emailMatcher.matches()) {
			return "Email is not Valid , it should contain letters ,numbers and @ char. ";
		} else if (!numberMatcher.matches()) {
			return "phn number is not valid , please enter only numbers with min. 10 digits";
		} else if (!passwordMatcher.matches()) {
			return "password is not Valid \n Must contain one Upper case One Lower Case One digit One special Charcter with min. length of 8";
		} else {
			return "ALL OK";
		}

	}

	public static String loginValidations(Users_ users) {

		Matcher emailMatcher = emailPattern.matcher(users.getEmail());
		Matcher passwordMatcher = passwordPattern.matcher(users.getPassword());

		if (!emailMatcher.matches()) {
			return "Email should not empty, it should contain letters ,numbers and @ char. ";
		} else if (!passwordMatcher.matches()) {
			return "password is should not be Empty Must contain one Upper case One Lower Case One digit One special Charcter with min. length of 8";
		} else {
			return "ALL OK";
		}

	}
}
