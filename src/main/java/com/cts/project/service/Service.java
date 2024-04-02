package com.cts.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.project.entity.Client;
import com.cts.project.entity.Project;
import com.cts.project.entity.Resource;
import com.cts.project.exception.GlobalExceptionHandler;
import com.cts.project.exception.MaximumResourceLimitReachedException;
import com.cts.project.exception.ResourceNotFoundException;
import com.cts.project.repository.ClientRepository;
import com.cts.project.repository.ProjectRepository;
import com.cts.project.repository.ResourceRepository;

@org.springframework.stereotype.Service
public class Service {
	
	@Autowired
	private ClientRepository clientRepo;
	
	@Autowired
	private ProjectRepository projectRepo;
	
	@Autowired
	private ResourceRepository resourceRepo;
	
	public static String prefix;
	
	
	
	public List<Client> getAllClients(){
		List<Client> allclients = (List<Client>) clientRepo.findAll();
		return allclients;
	}
	
	public void addProject(Project project) {
		projectRepo.save(project);
	}
	
	public Resource addResource(Resource resource) throws MaximumResourceLimitReachedException,ResourceNotFoundException{
		prefix = resource.getLastName().substring(0, 2).toUpperCase();	
		Project project=projectRepo.findById(resource.getProjectCode()).get(); 
		String projectStatus=project.getStatus().toLowerCase();
		if(projectStatus.equals("cancelled")) {
			throw new MaximumResourceLimitReachedException("Cannot add resource to Cancelled Project");
		}
		else {
			
		if(resource.getRole().equals("Tester")) {
			int numberOfTester=resourceRepo.getNumberOfTesters(resource.getProjectCode());
//			int numberOfTester=10;
		
			if(numberOfTester>=10) {
				throw new MaximumResourceLimitReachedException("Each project is allowed a maximum of 10 testers");
			}else {
			return resourceRepo.save(resource);
			}
		}else {
			int numberOfDeveloper=resourceRepo.getNumberOfDevelopers(resource.getProjectCode());
		
			if(numberOfDeveloper>=50) {
				throw new MaximumResourceLimitReachedException("Each project is allowed a maximum of 50 developers");
			}else {
			return resourceRepo.save(resource);
			}
		}
		}
		
	}

	public List<Project> getAllProjects() {
		Iterable<Project> allProjects = projectRepo.findAll();
		return (List<Project>) allProjects;
	}

	
	public String updateProjectStatus(int projectCode,Project project) throws ResourceNotFoundException {
		
		
		Optional<Project> opt = projectRepo.findById(projectCode);
		if (opt.isEmpty()) {
			throw new ResourceNotFoundException("NO PROJECT FOUND");
		} else {
			projectRepo.save(project);
			return "success";
		}
		
	}

}
