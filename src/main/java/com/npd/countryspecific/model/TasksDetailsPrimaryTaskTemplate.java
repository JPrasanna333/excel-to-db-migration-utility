package com.npd.countryspecific.model;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "SHRDNPDLookUpDomainModelTasks_DetailsTask_Template983")
public class TasksDetailsPrimaryTaskTemplate {
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tasks_DetailId96EB29F05DE824E8")
	private String projectClassification;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Task_Template_Id")
	private String taskDetails;
	
	@Column(name = "s_organizationid")
    private int organizationid;

}
