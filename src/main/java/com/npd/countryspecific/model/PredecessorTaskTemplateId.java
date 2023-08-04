package com.npd.countryspecific.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PredecessorTaskTemplateId implements Serializable {
	private Integer taskDetail;
	private Integer taskTemplate;

	public int hashCode() {
		return (int) (taskDetail + taskTemplate);
	}

	public boolean equals(Object object) {
		if (object instanceof PredecessorTaskTemplateId) {
			PredecessorTaskTemplateId otherId = (PredecessorTaskTemplateId) object;
			return (otherId.taskDetail == this.taskDetail)
					&& (otherId.taskTemplate == this.taskTemplate);
		}
		return false;
	}

}
