package sysats.protocol;

public class ChangeUsernameProtocol extends Protocol {
	private String newUsername = null;
	
	public ChangeUsernameProtocol() {
	
	}
	
	public ChangeUsernameProtocol(String newUsername) {
		this.newUsername = newUsername;
	}
	
	public String getNewUsername() {
		return newUsername;
	}
}
