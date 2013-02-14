package sysats.server;

//protocol timestamp:username:message

public class Protocol {
	private String timestamp = null;
	private String username = null;
	private String message = null;
	private String fullMessage = null;

	public Protocol() {

	}

	public Protocol(String fullMessage) {
		this.fullMessage = fullMessage;
	}

	public void setMessage(String message) {
		this.fullMessage = message;
		String temp = this.fullMessage;
		this.timestamp = temp.substring(0, temp.indexOf('?'));
		temp = temp.substring(temp.indexOf('?') + 1);// sitoj vietoj liko
														// username:message
		this.username = temp.substring(0, temp.indexOf('?'));
		temp = temp.substring(temp.indexOf('?') + 1);// sitoj vietoj liko message
														
		this.message = temp;

	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public String getUsername() {
		return this.username;
	}

	public String getMessage() {
		return this.message;
	}

	public String getFullMessage() {
		return this.fullMessage;
	}

}
