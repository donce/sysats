package sysats.server;

import java.util.ArrayList;
import java.util.Vector;

//protocol timestamp:username:message
public class ServerDispatcher extends Thread {
	private Vector<Protocol> messageQueue = new Vector<Protocol>();// zinuciu
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
			Protocol protocol) {
		messageQueue.add(protocol);
		notify();
	}

	private synchronized Protocol getNextMessageFromQueue()
			throws InterruptedException {
		while (messageQueue.size() == 0)
			wait();
		Protocol protocol = messageQueue.get(0);
		messageQueue.removeElementAt(0);
		return protocol;
	}

	private synchronized void sendMessageToAllClients(Protocol protocol) {
		for (int i = 0; i < serverClients.size(); i++) {
			ClientInfo clientInfo = serverClients.get(i);
			clientInfo.clientSender.sendMessage(protocol);
		}
	}

	public void run() {
		try {
			while (true) {
				Protocol protocol = getNextMessageFromQueue();
				sendMessageToAllClients(protocol);
			}
		} catch (InterruptedException ie) {
		}
	}
}