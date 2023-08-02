package com.npd.countryspecific.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="shrdnpdlookupdomainmodeltask_template")
public class TaskTemplate {

	@Id
	@Column(name = "Id")
	private Integer id;
	
	@Column(name="taskname")
	private String taskName;
	
	@Column(name="taskdescription")
	private String taskDescription;
	
	@Column(name="defaultduration")
	private Integer defaultDuration;
	
	@Column(name="ischeckpointtask")
	private Boolean isCheckPointTask;
	
	@Column(name="isactive")
	private Boolean isActive;
	
	@Column(name="fgorconc")
	private String fgOrConc;
	
	
	@Column(name="isregistrationtask")
	private String isRegistrationTask;
	
	@Column(name="ismanagerialtask")
	private String isManagerialTask;
}

