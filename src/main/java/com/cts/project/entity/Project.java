package com.cts.project.entity;



import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Projects")
public class Project {
	
	@Id
	@Column(name = "project_code")
	@GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private int projectCode;
	private String title;
	private int budget;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expectedEndDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdOn;
	
	private String status;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lastUpdatedOn;
	
	@Column(name = "client_id")
	private int clientId;
	
//	@ManyToOne(optional = true)
//	@JoinColumn(name="client_id",referencedColumnName = "id")
//	private Client client;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_code", referencedColumnName = "project_code", insertable = false, updatable = false)
	private List<Resource> resource;

	public int getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(int projectCode) {
		this.projectCode = projectCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(Date expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}

	public Project(int projectCode, String title, int budget, Date startDate, Date expectedEndDate, Date createdOn,
			String status, Date lastUpdatedOn, int clientId) {
		super();
		this.projectCode = projectCode;
		this.title = title;
		this.budget = budget;
		this.startDate = startDate;
		this.expectedEndDate = expectedEndDate;
		this.createdOn = createdOn;
		this.status = status;
		this.lastUpdatedOn = lastUpdatedOn;
		this.clientId = clientId;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	

}
