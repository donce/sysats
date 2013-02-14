package sysats.server;

import java.util.ArrayList;
import java.util.Vector;

//protocol timestamp:username:message
public class ServerDispatcher extends Thread {
	private Vector<String> messageQueue = new Vector<String>();// zinuciu
																// eile
	private ArrayList<ClientInfo> serverClients = new ArrayList<ClientInfo>();// naudotojai,

	public synchronized void addClient(ClientInfo clientInfo) {
		serverClients.add(clientInfo);
	}

	public synchronized void removeClient(ClientInfo clientInfo) {
		int clientIndex = serverClients.indexOf(clientInfo);
		if (clientIndex != -1) {
			serverClients.remove(clientIndex);
		}
	}

	public synchronized void dispatchMessage(ClientInfo clientInfo,
			String message) {
		messageQueue.add(message);
		notify();
	}

	private synchronized String getNextMessageFromQueue()
			throws InterruptedException {
		while (messageQueue.size() == 0)
			wait();
		String message = messageQueue.get(0);
		messageQueue.removeElementAt(0);
		return message;
	}

	private synchronized void sendMessageToAllClients(String message) {
		for (int i = 0; i < serverClients.size(); i++) {
			ClientInfo clientInfo = serverClients.get(i);
			clientInfo.clientSender.sendMessage(message);
		}
	}

	public void run() {
		try {
			while (true) {
				String message = getNextMessageFromQueue();
				sendMessageToAllClients(message);
			}
		} catch (InterruptedException ie) {
		}
	}
}