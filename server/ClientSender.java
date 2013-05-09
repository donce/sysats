package sysats.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import sysats.protocol.Protocol;

public class ClientSender extends Thread {
	private Vector<Protocol> messageQueue = new Vector<Protocol>();

	private ServerDispatcher serverDispatcher;
	private ClientInfo clientInfo;
	private ObjectOutputStream out;

	public ClientSender(ClientInfo clientInfo, ServerDispatcher serverDispatcher)
			throws IOException {
		this.clientInfo = clientInfo;
		this.serverDispatcher = serverDispatcher;
		Socket socket = clientInfo.clientSocket;
		this.out = new ObjectOutputStream(socket.getOutputStream());
	}

	public synchronized void sendMessage(Protocol protocol) {
		messageQueue.add(protocol);
		notify();
	}

	private synchronized Protocol getNextMessageFromQueue()
			throws InterruptedException {
		while (messageQueue.size() == 0)
			wait();
		Protocol protocol = (Protocol)messageQueue.get(0);
		messageQueue.removeElementAt(0);
		return protocol;
	}

	private void sendMessageToClient(Protocol protocol) {
		try {
			out.writeObject(protocol);
			out.flush();
		} catch (IOException e) {
		}
	}

	public void run() {
		try {
			while (!isInterrupted()) {
				Protocol protocol = getNextMessageFromQueue();
				sendMessageToClient(protocol);
			}
		} catch (Exception e) {
		}

		clientInfo.clientListener.interrupt();
		serverDispatcher.removeClient(clientInfo);
	}

}