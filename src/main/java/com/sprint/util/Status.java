package com.sprint.util;

import lombok.Data;

@Data
public class Status {
	public Status(String status) {
		this.status = status;
	}

	String status;
	int count;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
