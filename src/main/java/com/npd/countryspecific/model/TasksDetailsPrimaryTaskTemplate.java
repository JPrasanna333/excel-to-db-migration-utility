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
//@Table(name = "SHRDNPDLookUpDomainModelTasks_DetailsTask_Template983")
@Table(name = "o4NPDLookUpDomainModelTasks_DetailsTask_Template559")
public class TasksDetailsPrimaryTaskTemplate {
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "Tasks_DetailId96EB29F05DE824E8")
	@JoinColumn(name = "TASKS_DETAILID9670E172181B4E7C")
	
	private String taskDetail;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Task_Template_Id")
	private String taskTemplate;
	
	@Column(name = "s_organizationid")
    private int organizationid;

}
