package com.cts.project.entity;

import java.io.Serializable;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "Resources")
public class Resource implements Serializable{
	
	@Id
	@GenericGenerator(name="userIdGenerator",strategy = "com.cts.project.idgenerator.UserIdGenerator")
	@GeneratedValue(generator = "userIdGenerator")
	private String userId;
	
	@Size(min=3,message = "Minimum 3 alphabets required")
	@Pattern(regexp = "[a-zA-Z]+", message = "Invalid characters found. Only alphabetic characters are allowed.")
	@NotNull
	private String firstName;
	
	@Size(min=3,message = "Minimum 3 alphabets required")
	private String lastName;
	
	@Pattern(regexp = "[a-zA-Z0-9._%+-]+@cognizant\\.com", message = "Email address should have the domain '@cognizant.com'")
	private String email;
	
	@Size(min=10,max=10)
	private String phoneNumber;
	
	private String role;
	
	@Column(name="project_code")
	private int projectCode;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}

	public Resource(String userId,
			@Size(min = 3, message = "Minimum 3 alphabets required") @Pattern(regexp = "[a-zA-Z]+", message = "Invalid characters found. Only alphabetic characters are allowed.") @NotNull String firstName,
			@Size(min = 3, message = "Minimum 3 alphabets required") String lastName,
			@Pattern(regexp = "[a-zA-Z0-9._%+-]+@cognizant\\.com", message = "Email address should have the domain '@cognizant.com'") String email,
			@Size(min = 10, max = 10) String phoneNumber, String role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resource(
			@Size(min = 3, message = "Minimum 3 alphabets required") @Pattern(regexp = "[a-zA-Z]+", message = "Invalid characters found. Only alphabetic characters are allowed.") @NotNull String firstName,
			@Size(min = 3, message = "Minimum 3 alphabets required") String lastName,
			@Pattern(regexp = "[a-zA-Z0-9._%+-]+@cognizant\\.com", message = "Email address should have the domain '@cognizant.com'") String email,
			@Size(min = 10, max = 10) String phoneNumber, String role, int projectCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.projectCode = projectCode;
	}


	
}
