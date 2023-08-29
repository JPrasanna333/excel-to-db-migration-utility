package com.npd.countryspecific.model;

import lombok.Data;

@Data
public class RequestBodyData {
	private int startValue;
	private int endValue;

	public int getstartValue() {
		return startValue;
	}

	public void setstartValue(int startValue) {
		this.startValue = startValue;
	}

	public int getendValue() {
		return endValue;
	}

	public void setendValue(int endValue) {
		this.endValue = endValue;
	}
}