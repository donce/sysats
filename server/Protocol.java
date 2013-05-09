package sysats.server;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

//protocol timestamp:username:message

public class Protocol implements Serializable {
	private Timestamp timestamp = null;
	private String username = null;
	
	private int type = 0;
	
	private String string = null;	
	private byte[] data = null;
	
	private static final Date date = new Date();
	
	public Protocol() {

	}

	public Protocol(String username, String message) {
		this.type = 1;
		this.username = username;
		this.string = message;
	}
	
	public Protocol(String username, String filename, byte[] data) {
		this.type = 2;
		this.username = username;
		this.string = filename;
		this.data = data;
	}

	public void updateTime() {
		this.timestamp = new Timestamp(date.getTime());
	}
	
	public int getType() {
		return this.type;
	}

	public String getTime() {
		return this.timestamp.toString();
	}

	public String getUsername() {
		return this.username;
	}

	//for message
	public String getMessage() {
		return this.string;
	}
	
	//for file
	public String getFilename() {
		return this.string;
	}
	
	public byte[] getData() {
		return this.data;
	}

}
