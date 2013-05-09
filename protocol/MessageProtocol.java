package sysats.protocol;

import java.sql.Timestamp;

public class MessageProtocol extends Protocol {
	private String message = null;

	public MessageProtocol() {
		
	}
	
	public MessageProtocol(String username, String message) {
		super(username);
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
	
}
