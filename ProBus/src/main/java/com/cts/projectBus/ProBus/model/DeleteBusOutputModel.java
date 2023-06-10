package com.cts.projectBus.ProBus.model;

public class DeleteBusOutputModel {
	 private String message;

	    public DeleteBusOutputModel(String message) {
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
}
