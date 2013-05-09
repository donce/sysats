package sysats.server;

import java.net.Socket;

public class ClientInfo {
	public Socket clientSocket = null;
	public ClientListener clientListener = null;
	public ClientSender clientSender = null;
	public String username = "Bevardis";
}