package com.npd.countryspecific.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectClassificationtTaskDetailsId implements Serializable {
	private Integer projectClassification;
	private Integer taskDetails;

	public int hashCode() {
		return (int) (projectClassification + taskDetails);
	}

	public boolean equals(Object object) {
		if (object instanceof ProjectClassificationtTaskDetailsId) {
			ProjectClassificationtTaskDetailsId otherId = (ProjectClassificationtTaskDetailsId) object;
			return (otherId.projectClassification == this.projectClassification)
					&& (otherId.taskDetails == this.taskDetails);
		}
		return false;
	}

}
