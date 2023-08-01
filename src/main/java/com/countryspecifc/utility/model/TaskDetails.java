package com.countryspecifc.utility.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="SHRDNPDLookUpDomainModelTasks_Details")
public class TaskDetails {

	@OneToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "R_PO_TASK_Id", referencedColumnName = "Id", insertable = false, updatable = false)
    private ProjectType TaskTemplate;
	
	@Column(name="Duration")
	private Integer duration;
	
	@Column(name="TaskSequence")
	private Integer taskSequence;
	
	
}
