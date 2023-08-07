package com.npd.countryspecific.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shrdnpdlookupdomainmodelproject_classificationtasks_details")
//@Table(name = "o4npdlookupdomainmodelproject_classificationtasks_details")
@IdClass(ProjectClassificationtTaskDetailsId.class)
@Entity
public class ProjectClassificationTasksDetails {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_CLASID83F7D33723BC5560")
//	@JoinColumn(name = "PROJECT_CLASID810D5EBEB9CF8AB5")
	private ProjectClassification projectClassification;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Tasks_Details_Id")
	private TasksDetails taskDetails;

	@Column(name = "s_organizationid")
	private int organizationid;
}
