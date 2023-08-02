package com.npd.countryspecific.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@Table(name="shrdnpdlookupdomainmodeltask_template")
@Table(name="o4npdlookupdomainmodeltask_template")
public class TaskTemplate {

	@Id
	@Column(name = "Id")
	private Integer id;
	
	@Column(name="TaskName")
	private String taskName;
	
	@Column(name="TaskDescription")
	private String taskDescription;
	
	@Column(name="DefaultDuration")
	private Integer defaultDuration;
	
	@Column(name="IsCheckPointTask")
	private Boolean isCheckPointTask;
	
	@Column(name="IsActive")
	private Boolean isActive;
	
	@Column(name="FgOrConc")
	private String fgOrConc;
	
	
	@Column(name="IsRegistrationTask")
	private String isRegistrationTask;
	
	@Column(name="IsManagerialTask")
	private String isManagerialTask;
	
	@OneToMany(mappedBy = "tasksDetails", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<TasksDetailsPredecessorTaskTemplate> tasksDetailsTemplates = new HashSet<TasksDetailsPredecessorTaskTemplate>();
	
	@OneToMany(mappedBy = "tasksDetails", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<TasksDetailsPrimaryTaskTemplate> taskPrimaryTaskTemplates = new HashSet<TasksDetailsPrimaryTaskTemplate>();
}

