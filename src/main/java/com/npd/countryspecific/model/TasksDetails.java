package com.npd.countryspecific.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@Table(name="shrdnpdlookupdomainmodeltasks_details")
@Table(name="o4npdlookupdomainmodeltasks_details")
public class TasksDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "R_PO_TASK_Id", referencedColumnName = "Id", insertable = false, updatable = false)
    private ProjectType TaskTemplate;
	
	@Column(name="Duration")
	private Integer duration;
	
	@Column(name="TaskSequence")
	private Integer taskSequence;
	
	@Column(name="R_PO_RM_ROLE_Id")
	private Integer rmRoleId;
	
	@Column(name="R_PO_TASK_OWNER_ROLE_Id")
	private Integer taskRoleId;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
//  @JoinTable(name = "shrdnpdlookupdomainmodeltasks_detailstask_template",
//  joinColumns = {
//          @JoinColumn(name = "TASKS_DETAILID979B2426C2669354", referencedColumnName = "Id",
//                  nullable = false, updatable = false)},
//  inverseJoinColumns = {
//          @JoinColumn(name = "Task_Template_Id",referencedColumnName = "Id",
//                  nullable = false, updatable = false)})
    @JoinTable(name = "o4npdlookupdomainmodeltasks_detailstask_template",
            joinColumns = {
                    @JoinColumn(name = "TASKS_DETAILIDA9B51D1974129FFD", referencedColumnName = "Id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "Task_Template_Id",referencedColumnName = "Id",
                            nullable = false, updatable = false)})
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
    @JoinTable(name = "o4NPDLookUpDomainModelTasks_DetailsTask_Template559",
            joinColumns = {
                    @JoinColumn(name = "TASKS_DETAILID9670E172181B4E7C", referencedColumnName = "Id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "Task_Template_Id",referencedColumnName = "Id",
                            nullable = false, updatable = false)})
    private Set<TaskTemplate> primaryDependentTaskList = new HashSet<TaskTemplate>();
	
	
}
