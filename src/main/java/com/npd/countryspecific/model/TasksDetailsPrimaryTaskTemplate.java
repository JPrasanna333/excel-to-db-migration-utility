package com.npd.countryspecific.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(PrimaryTaskTemplateId.class)
@Table(name = "shrdnpdlookupdomainmodeltasks_detailstask_template983")
//@Table(name = "o4npdlookupdomainmodeltasks_detailstask_template391")
public class TasksDetailsPrimaryTaskTemplate {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Tasks_Detailid96EB29F05DE824E8")
//	@JoinColumn(name = "Tasks_DetailidA886B77B1F3E2202")
	private TasksDetails taskDetail;

	@Id
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Task_Template_Id")
	private TaskTemplate taskTemplate;

	@Column(name = "s_organizationid")
	private int organizationid;

}
