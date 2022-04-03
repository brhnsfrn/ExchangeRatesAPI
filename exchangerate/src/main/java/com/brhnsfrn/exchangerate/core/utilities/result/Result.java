package com.brhnsfrn.exchangerate.core.utilities.result;

public class Result {
	private boolean success;
	
	public Result(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return this.success;
	}
}