package sysats.protocol;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


public class Protocol implements Serializable {
	private static final Date date = new Date();

	private Timestamp timestamp = null;
	private String username = null;
	
	public Protocol() {
	}
	
	public void updateTime() {
		this.timestamp = new Timestamp(date.getTime());
	}
	
	public String getTime() {
		return this.timestamp.toString();
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

}
