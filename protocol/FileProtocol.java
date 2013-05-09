package sysats.protocol;

public class FileProtocol extends Protocol {
	private String filename = null;
	private byte[] data = null;
	
	public FileProtocol() {
	
	}
	
	public FileProtocol(String filename, byte[] data) {
		this.filename = filename;
		this.data = data;
	}

	public String getFilename() {
		return this.filename;
	}
	
	public byte[] getData() {
		return this.data;
	}
}
