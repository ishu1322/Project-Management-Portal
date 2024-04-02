package com.cts.project.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@javax.persistence.Entity
@javax.persistence.Table(name="Clients")
public class Client {
	
	@javax.persistence.Id
	@Column(name="id")
//	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private int clientId;
	
	@Column(name="Name")
	private String clientName;
	
	private String pocFullName;
	
	private String pocPhoneNumber;
	
	@Column(name="POC_EmailAddress")
	private String pocEmail;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
	private List<Project> project;

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getPocFullName() {
		return pocFullName;
	}

	public void setPocFullName(String pocFullName) {
		this.pocFullName = pocFullName;
	}

	public String getPocPhoneNumber() {
		return pocPhoneNumber;
	}

	public void setPocPhoneNumber(String pocPhoneNumber) {
		this.pocPhoneNumber = pocPhoneNumber;
	}

	public String getPocEmail() {
		return pocEmail;
	}

	public void setPocEmail(String pocEmail) {
		this.pocEmail = pocEmail;
	}

	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}

	public Client(int clientId, String clientName, String pocFullName, String pocPhoneNumber, String pocEmail) {
		super();
		this.clientId = clientId;
		this.clientName = clientName;
		this.pocFullName = pocFullName;
		this.pocPhoneNumber = pocPhoneNumber;
		this.pocEmail = pocEmail;
	}

	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
