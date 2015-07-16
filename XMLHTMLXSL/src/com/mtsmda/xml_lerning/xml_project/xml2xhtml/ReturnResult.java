package com.mtsmda.xml_lerning.xml_project.xml2xhtml;

import java.io.Serializable;

class ReturnResult implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer success;
	private String filename;
	
	public ReturnResult() {
		
	}

	public ReturnResult(Integer success, String filename) {
		this.success = success;
		this.filename = filename;
	}
	
	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}