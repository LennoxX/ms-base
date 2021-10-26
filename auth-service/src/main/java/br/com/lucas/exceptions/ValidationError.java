package br.com.lucas.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

	private Integer status;

	private String msg;

	private List<FieldMessage> list = new ArrayList<>();

	public ValidationError(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FieldMessage> getList() {
		return list;
	}

	public void addError(FieldMessage message) {
		this.list.add(message);

	}

}
