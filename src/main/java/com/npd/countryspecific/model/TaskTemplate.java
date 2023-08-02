package com.npd.countryspecific.model;

<<<<<<< .mine



=======
import java.util.HashSet;
import java.util.Set;

>>>>>>> .theirs
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
<<<<<<< .mine


=======
import javax.persistence.FetchType;

>>>>>>> .theirs
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
	
	@OneToMany(mappedBy = "tasksDetails", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<TasksDetailsPredecessorTaskTemplate> tasksDetailsTemplates = new HashSet<TasksDetailsPredecessorTaskTemplate>();
	
	@OneToMany(mappedBy = "tasksDetails", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Set<TasksDetailsPrimaryTaskTemplate> taskPrimaryTaskTemplates = new HashSet<TasksDetailsPrimaryTaskTemplate>();
}

