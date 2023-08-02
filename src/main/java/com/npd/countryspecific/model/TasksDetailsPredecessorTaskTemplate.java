package com.npd.countryspecific.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="shrdnpdlookupdomainmodeltasks_detailstask_template")
public class TasksDetailsPredecessorTaskTemplate {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tasks_DetailId979B2426C2669354")
	private String projectClassification;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Task_Template_Id")
	private String taskDetails;
	
	@Column(name = "s_organizationid")
    private int organizationid;
}
