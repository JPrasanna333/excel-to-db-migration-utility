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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shrdnpdlookupdomainmodeltask_template")
//@Table(name="o4npdlookupdomainmodeltask_template")
public class TaskTemplate {

	@Id
	@Column(name = "Id")
	private Integer id;

	@Column(name = "taskname")
	private String taskName;

	@Column(name = "taskdescription")
	private String taskDescription;

	@Column(name = "defaultduration")
	private Integer defaultDuration;

	@Column(name = "ischeckpointtask")
	private Boolean isCheckPointTask;

	@Column(name = "isactive")
	private Boolean isActive;

	@Column(name = "fgorconc")
	private String fgOrConc;

	@Column(name = "isregistrationtask")
	private Boolean isRegistrationTask;

	@Column(name = "ismanagerialtask")
	private Boolean isManagerialTask;

	@OneToMany(mappedBy = "taskTemplate", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Set<TasksDetailsPredecessorTaskTemplate> tasksDetailsTemplates = new HashSet<TasksDetailsPredecessorTaskTemplate>();

	@OneToMany(mappedBy = "taskTemplate", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Set<TasksDetailsPrimaryTaskTemplate> taskPrimaryTaskTemplates = new HashSet<TasksDetailsPrimaryTaskTemplate>();
}
