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
//@Table(name="shrdnpdlookupdomainmodelproject_classification")
@Table(name = "o4npdlookupdomainmodelproject_classification")
public class ProjectClassification {
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "projectclassificationnumber")
	private String projectClassificationNumber;

	@Column(name = "projectclassificationdescription")
	private String projectClassificationDescription;

	@Column(name = "newfG")
	private Boolean newFg;

	@Column(name = "newhbc")
	private Boolean newHbc;

	@Column(name = "newrd")
	private Boolean newRd;

	@Column(name = "primarypackaging")
	private Boolean primaryPackaging;

	@Column(name = "secondarypackaging")
	private Boolean secondaryPackaging;

	@OneToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "R_PO_PROJECT_TYPE_Id", referencedColumnName = "Id", insertable = true, updatable = false)
	private ProjectType projectType;

	@Column(name = "R_PO_REGION_Id")
	private Integer region;
	
	@Column(name = "S_ITEM_STATUS")
	private Integer itemStatusId;

	@ManyToMany(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
//  @JoinTable(name = "shrdnpdlookupdomainmodelproject_classificationtasks_details",
//  joinColumns = {
//          @JoinColumn(name = "PROJECT_CLASID83F7D33723BC5560", referencedColumnName = "Id",
//                  nullable = false, updatable = false)},
//  inverseJoinColumns = {
//          @JoinColumn(name = "Tasks_Details_Id",referencedColumnName = "Id",
//                  nullable = false, updatable = false)})
	@JoinTable(name = "o4npdlookupdomainmodelproject_classificationtasks_details", joinColumns = {
			@JoinColumn(name = "PROJECT_CLASID810D5EBEB9CF8AB5", referencedColumnName = "Id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "Tasks_Details_Id", referencedColumnName = "Id", nullable = false, updatable = false) })
	private Set<TasksDetails> taskDetails = new HashSet<TasksDetails>();

	@OneToMany(mappedBy = "projectClassification", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private Set<ProjectClassificationTasksDetails> projectClassificationTasksDetails = new HashSet<ProjectClassificationTasksDetails>();

}
