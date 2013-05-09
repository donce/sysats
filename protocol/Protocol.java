package sysats.protocol;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

//protocol timestamp:username:message

public class Protocol implements Serializable {
	private static final Date date = new Date();

	private Timestamp timestamp = null;
	private String username = null;
	
	public Protocol() {
	}
	
	public Protocol(String username) {
		this.username = username;
	}

	public void updateTime() {
		this.timestamp = new Timestamp(date.getTime());
	}
	
	public String getTime() {
		return this.timestamp.toString();
	}

	public String getUsername() {
		return this.username;
	}


}
