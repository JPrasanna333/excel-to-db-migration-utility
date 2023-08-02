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
@Table(name="shrdnpdlookupdomainprojectclassification")
public class ProjectClassification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name="ProjectClassificationNumber")
	private Integer projectClassificationNumber;
	
	
	@Column(name="ProjectClassificationDescription")
	private String projectClassificationDescription;
	
	@Column(name="NewFG")
	private Boolean newFg;
	
	@Column(name="NewHBC")
	private Boolean newHbc;
	
	@Column(name="NewRD")
	private Boolean newRd;
	
	@Column(name="PrimaryPackaging")
	private Boolean primaryPackaging;
	
	@Column(name="SecondaryPackaging")
	private Boolean secondaryPackaging;
	
	
	@OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "R_PO_PROJECT_TYPE_Id", referencedColumnName = "Id", insertable = true, updatable = false)
    private ProjectType projectType;
	
	@OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "R_PO_REGION_Id", referencedColumnName = "Id", insertable = true, updatable = false)
    private Region region;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinTable(name = "shrdnpdlookupdomainmodelproject_classificationtasks_details",
            joinColumns = {
                    @JoinColumn(name = "PROJECT_CLASID83F7D33723BC5560", referencedColumnName = "Id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "Tasks_Details_Id",referencedColumnName = "Id",
                            nullable = false, updatable = false)})
    private Set<TasksDetails> taskList = new HashSet<TasksDetails>();
	
	
	
}
