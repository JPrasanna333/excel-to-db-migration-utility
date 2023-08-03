package com.npd.countryspecific.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
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
@Entity
@ToString
//@Table(name="shrdnpdlookupdomainmodeltasks_detailstask_template")
@Table(name="o4npdlookupdomainmodeltasks_detailstask_template")
public class TasksDetailsPredecessorTaskTemplate implements Serializable   {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "Tasks_DetailId979B2426C2669354")
    @JoinColumn(name = "TASKS_DETAILIDA9B51D1974129FFD")
	private TasksDetails taskDetail;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Task_Template_Id")
	private TaskTemplate taskTemplate;
	
	@Column(name = "s_organizationid")
    private int organizationid;
}
