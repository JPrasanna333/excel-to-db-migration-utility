package com.npd.countryspecific.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name="shrdnpdlookupdomainmodeltasks_details")
@Table(name = "o4npdlookupdomainmodeltasks_details")
public class TasksDetails {
	@Id
	@Column(name = "Id")
	private Integer id;

	@OneToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "R_PO_TASK_Id", referencedColumnName = "Id", insertable = true, updatable = false)
	private TaskTemplate tasktemplate;

	@Column(name = "duration")
	private Integer duration;

	@Column(name = "tasksequence")
	private Integer taskSequence;

	@Column(name = "R_PO_RM_ROLE_ID")
	private Integer rmRoleId;

	@Column(name = "R_PO_TASK_OWNER_ROLE_Id")
	private Integer taskOwnerId;

	@OneToMany(mappedBy = "taskDetails", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Set<ProjectClassificationTasksDetails> projectClassificationTasksDetails = new HashSet<ProjectClassificationTasksDetails>();

	@ManyToMany(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
//  @JoinTable(name = "shrdnpdlookupdomainmodeltasks_detailstask_template",
//  joinColumns = {
//          @JoinColumn(name = "TASKS_DETAILID979B2426C2669354", referencedColumnName = "Id",
//                  nullable = false, updatable = false)},
//  inverseJoinColumns = {
//          @JoinColumn(name = "Task_Template_Id",referencedColumnName = "Id",
//                  nullable = false, updatable = false)})
	@JoinTable(name = "o4npdlookupdomainmodeltasks_detailstask_template", joinColumns = {
			@JoinColumn(name = "Tasks_Detailida62CFA91996C7B8B", referencedColumnName = "Id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Task_Template_Id", referencedColumnName = "Id", nullable = false, updatable = false) })
	private Set<TaskTemplate> predecessorTaskList = new HashSet<TaskTemplate>();

	@ManyToMany(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
//  @JoinTable(name = "SHRDNPDLookUpDomainModelTasks_DetailsTask_Template983",
//  joinColumns = {
//          @JoinColumn(name = "TASKS_DETAILID96EB29F05DE824E8", referencedColumnName = "Id",
//                  nullable = false, updatable = false)},
//  inverseJoinColumns = {
//          @JoinColumn(name = "Task_Template_Id",referencedColumnName = "Id",
//                  nullable = false, updatable = false)})
	@JoinTable(name = "o4npdlookupdomainmodeltasks_detailstask_template391", joinColumns = {
			@JoinColumn(name = "Tasks_DetailidA886B77B1F3E2202", referencedColumnName = "Id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Task_Template_Id", referencedColumnName = "Id", nullable = false, updatable = false) })
	private Set<TaskTemplate> primaryDependentTaskList = new HashSet<TaskTemplate>();

	@Column(name = "S_ITEM_STATUS")
	private Integer itemStatusId;

	@OneToMany(mappedBy = "taskDetail", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Set<TasksDetailsPredecessorTaskTemplate> pretasksTemplates = new HashSet<TasksDetailsPredecessorTaskTemplate>();

	@OneToMany(mappedBy = "taskDetail", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Set<TasksDetailsPrimaryTaskTemplate> taskPrimaryTaskTemplates = new HashSet<TasksDetailsPrimaryTaskTemplate>();

}
