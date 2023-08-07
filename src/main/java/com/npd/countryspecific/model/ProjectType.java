package com.npd.countryspecific.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "shrdnpdlookupdomainmodelproject_type")
//@Table(name = "o4npdlookupdomainmodelproject_type")
@Entity
public class ProjectType {

	@Id
	@Column(name = "Id", updatable = false)
	private String id;

	@Column(name = "DISPLAY_NAME", updatable = false)
	private String displayName;

	public ProjectType(String displayName) {
		super();
		this.displayName = displayName;
	}

}
