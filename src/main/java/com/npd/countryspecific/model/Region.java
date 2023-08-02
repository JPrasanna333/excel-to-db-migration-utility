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
//@Table(name = "shrdnpdlookupdomainmodelregion")
@Table(name = "o4npdlookupdomainmodelregion")
@Entity 

public class Region {

	
    @Id
    @Column(name = "Id", updatable = false)
    private String id;

    @Column(name = "displayname", updatable = false)
    private String displayName;

	public Region(String displayName) {
		super();
		this.displayName = displayName;
	}
    
    

}
