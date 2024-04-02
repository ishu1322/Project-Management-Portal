package com.cts.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.project.entity.Client;
import com.cts.project.entity.Project;
import com.cts.project.entity.Resource;
import com.cts.project.exception.MaximumResourceLimitReachedException;
import com.cts.project.exception.ResourceNotFoundException;
import com.cts.project.service.Service;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {
	
	@Autowired
	private Service service;
	
	
	
	@GetMapping("/clients")
	public List<Client> getAllClients(){
		return service.getAllClients();
		
	}
	
	@PostMapping("/projects/new")
	public void addPrjoect(@RequestBody Project project) {
		service.addProject(project);
	}
	
	@GetMapping("/projects")
	public List<Project> getAllProjects(){
		return service.getAllProjects();
	}
	
	@PutMapping("/projects/{projectCode}/update")
	public void updateProject(@PathVariable("projectCode") int projectCode ,@RequestBody Project project) throws ResourceNotFoundException {
		service.updateProjectStatus(projectCode,project);
	}
	
	@PostMapping("/projects/addresource")
	public Resource addResource(@Valid @RequestBody Resource resource) throws MaximumResourceLimitReachedException, ResourceNotFoundException {
		return service.addResource(resource);
	
	}
	
/*	

	 api documentation: http://localhost:1010/swagger-ui/index.html
	 
	{
	    "projectCode":20,
	    "title":"Project 1",
	    "budget":2000,
	    "startDate":"2000-01-01",
	    "expectedEndDate":"2000-12-01",
	    "createdOn":"2000-01-01",
	    "status":"New",
	    "lastUpdatedOn":"2000-01-05",
	    "clientId":101
	}
	

	{
	    "userId":21,
	    "firstName":"Ishu",
	    "lastName":"Dagar",
	    "email":"123@cognizant.com",
	    "phoneNumber":"9090909090",
	    "role":"Tester",
	    "projectCode":21

	}
	
	-------------------Resource-------------------------
	{
	    "userId":21,
	    "firstName":"Ishu",
	    "lastName":"Dagar",
	    "email":"123@cognizant.com",
	    "phoneNumber":"9090909090",
	    "role":"Tester",
	    "projectCode":20
	}
	
	*/
}
