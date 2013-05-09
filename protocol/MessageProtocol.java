package sysats.protocol;

import java.sql.Timestamp;

public class MessageProtocol extends Protocol {
	private String message = null;

	public MessageProtocol() {
		
	}
	
	public MessageProtocol(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
	
}
