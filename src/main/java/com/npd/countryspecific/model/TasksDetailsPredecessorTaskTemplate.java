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
@Entity
@IdClass(PredecessorTaskTemplateId.class)
@Table(name = "shrdnpdlookupdomainmodeltasks_detailstask_template")
//@Table(name = "o4npdlookupdomainmodeltasks_detailstask_template")
public class TasksDetailsPredecessorTaskTemplate {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Tasks_Detailid979B2426C2669354")
//	@JoinColumn(name = "Tasks_DetailidA62CFA91996C7B8B")
	private TasksDetails taskDetail;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Task_Template_Id")
	private TaskTemplate taskTemplate;

	@Column(name = "s_organizationid")
	private int organizationid;
}
