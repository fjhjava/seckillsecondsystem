package com.seckill.dto;

/**
 * 封装json结果
 * 
 * @author phantomfjh
 *
 * @param <T>
 */
public class SkillResult<T> {

	private boolean success;

	private T data;

	private String error;

	public SkillResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	public SkillResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
