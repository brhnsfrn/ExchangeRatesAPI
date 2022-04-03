package com.brhnsfrn.exchangerate.core.utilities.result;

public class DataResult<T> extends Result {
	private T data;
	
	public DataResult(T data) {
		super(true);
		this.data = data;
	}
	
	public DataResult(T data, boolean success) {
		super(success);
		this.data = data;
	}

	public T getData() {
		return this.data;
	}
}
