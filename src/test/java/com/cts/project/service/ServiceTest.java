package com.cts.project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.cts.project.entity.Client;
import com.cts.project.entity.Project;
import com.cts.project.entity.Resource;
import com.cts.project.exception.MaximumResourceLimitReachedException;
import com.cts.project.exception.ResourceNotFoundException;
import com.cts.project.repository.ClientRepository;
import com.cts.project.repository.ProjectRepository;
import com.cts.project.repository.ResourceRepository;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceTest {
	
	@Mock
	private ClientRepository clientRepo;
	
	@Mock
	private ProjectRepository projectRepo;
	
	@Mock
	private ResourceRepository resourceRepo;
	
	@InjectMocks
	private Service service;

	

	@Test
	void testGetAllClients() {
		
		Client client1=new Client(101,"ABC","Client 1","9090909090","client1@abc.com");
		Client client2=new Client(102,"XYZ","Client 2","9090909090","client2@xyz.com");
		given(clientRepo.findAll()).willReturn(List.of(client1,client2));
	
		
//		action using service
		List<Client> allClients = service.getAllClients();
		
//		verify
		assertThat(allClients).isNotNull();
		assertThat(allClients.size()).isEqualTo(2);
	}
	
	@Test
	void getAllClients_ReturnEmptyList() {
		given(clientRepo.findAll()).willReturn(Collections.emptyList());
		
		List<Client> allClients = service.getAllClients();
		assertThat(allClients.size()).isEqualTo(0);
	}

	@Test
	void testAddProject() throws ParseException {
		
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date startDate = dateFormat.parse("2000-01-01");
		Date endDate = dateFormat.parse("2000-12-01");
		Date createdOn = dateFormat.parse("2000-02-01");
		Date lastUpdatedOn = dateFormat.parse("2000-01-05");
		
		
		Project project=new Project(20,"Project 1",2000,startDate,endDate,createdOn,"New",lastUpdatedOn,101);
		
		
		service.addProject(project);
		verify(projectRepo,times(1)).save(project);
		
	}

	
	@Test
	void testAddResource() throws MaximumResourceLimitReachedException, ParseException, ResourceNotFoundException {
		 Project project = new Project();
	     project.setStatus("New");
	     when(projectRepo.findById(anyInt())).thenReturn(Optional.of(project));
		Resource resource1=new Resource("2","Ishu","Dagar","ishu@cognizant","9090909090","Tester");
		Resource resource2=new Resource("2","Ram","Singh","ram@cognizant","9090909090","Developer");
		
		when(resourceRepo.save(resource1)).thenReturn(resource1);
		when(resourceRepo.save(resource2)).thenReturn(resource2);
		Resource responseResource1=service.addResource(resource1);
		Resource responseResource2=service.addResource(resource2);
		assertThat(responseResource1).isNotNull();
		assertThat(responseResource2).isNotNull();
		
		verify(resourceRepo,times(2)).save(any());
		
	}
	
	
	@Test
	void addResource_Tester_WhenThrowsMaximumLimitReached() throws MaximumResourceLimitReachedException, ParseException {
		
		Project project = new Project();
	     project.setStatus("New");
	     when(projectRepo.findById(anyInt())).thenReturn(Optional.of(project));
		
		when(resourceRepo.getNumberOfTesters(anyInt())).thenReturn(10);
		Resource resource = new Resource("2","Ishu","Dagar","ishu@cognizant","9090909090","Tester");
		assertThrows(MaximumResourceLimitReachedException.class, () -> {
            service.addResource(resource);
        });
		verify(resourceRepo).getNumberOfTesters(anyInt());
        verifyNoMoreInteractions(resourceRepo);
	}
	
	@Test
	void addResource_Developer_WhenThrowsMaximumLimitReached() throws MaximumResourceLimitReachedException, ParseException {
		
		Project project = new Project();
	     project.setStatus("New");
	     when(projectRepo.findById(anyInt())).thenReturn(Optional.of(project));
		when(resourceRepo.getNumberOfDevelopers(anyInt())).thenReturn(50);
		Resource resource = new Resource("2","Ishu","Dagar","ishu@cognizant","9090909090","Developer");
		assertThrows(MaximumResourceLimitReachedException.class, () -> {
            service.addResource(resource);
        });
		verify(resourceRepo).getNumberOfDevelopers(anyInt());
        verifyNoMoreInteractions(resourceRepo);
	}


	
	@Test
	void testGetAllProjects() throws ParseException {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date startDate = dateFormat.parse("2000-01-01");
		Date endDate = dateFormat.parse("2000-12-01");
		Date createdOn = dateFormat.parse("2000-02-01");
		Date lastUpdatedOn = dateFormat.parse("2000-01-05");

		Project project1=new Project(20,"Project 1",2000,startDate,endDate,createdOn,"New",lastUpdatedOn,101);
		Project project2=new Project(21,"Project 2",2000,startDate,endDate,createdOn,"InProgress",lastUpdatedOn,101);
		
		given(projectRepo.findAll()).willReturn(List.of(project1,project2));
		
		List<Project> allProjects = service.getAllProjects();
		
		assertThat(allProjects).isNotNull();
		assertThat(allProjects.size()).isEqualTo(2);
	}
	
	@Test
	void getAllProjects_ReturnEmptyList() {
		given(projectRepo.findAll()).willReturn(Collections.emptyList());
		List<Project> allProjects = service.getAllProjects();
		assertThat(allProjects.size()).isEqualTo(0);
	}

	
	@Test
	void testUpdateProjectStatus() throws ParseException, ResourceNotFoundException {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date startDate = dateFormat.parse("2000-01-01");
		Date endDate = dateFormat.parse("2000-12-01");
		Date createdOn = dateFormat.parse("2000-02-01");
		Date lastUpdatedOn = dateFormat.parse("2000-01-05");

		Project existingproject=new Project(20,"Project 1",2000,startDate,endDate,createdOn,"New",lastUpdatedOn,101);
		
		when(projectRepo.findById(20)).thenReturn(Optional.of(existingproject));
		
		Project updatedproject=new Project(20,"Project 1",2000,startDate,endDate,createdOn,"InProgress",lastUpdatedOn,101);
		String result = service.updateProjectStatus(20, updatedproject);
		
		verify(projectRepo).findById(20);
		verify(projectRepo,times(1)).save(updatedproject);
		assertEquals("success", result);
	}
	
	@Test
	void UpdateProject_WhenThrowsResourceNotFound() {
		
		when(projectRepo.findById(00)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, ()->{service.updateProjectStatus(00, new Project());});
	}
	

}
