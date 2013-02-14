package sysats.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class ClientSender extends Thread {
	private Vector<String> messageQueue = new Vector<String>();

	private ServerDispatcher serverDispatcher;
	private ClientInfo clientInfo;
	private PrintWriter out;

	public ClientSender(ClientInfo clientInfo, ServerDispatcher serverDispatcher)
			throws IOException {
		this.clientInfo = clientInfo;
		this.serverDispatcher = serverDispatcher;
		Socket socket = clientInfo.clientSocket;
		this.out = new PrintWriter(new OutputStreamWriter(
				socket.getOutputStream()));
	}

	public synchronized void sendMessage(String message) {
		messageQueue.add(message);
		notify();
	}

	private synchronized String getNextMessageFromQueue()
			throws InterruptedException {
		while (messageQueue.size() == 0)
			wait();
		String message = (String) messageQueue.get(0);
		messageQueue.removeElementAt(0);
		return message;
	}

	private void sendMessageToClient(String message) {
		out.println(message);
		out.flush();
	}

	public void run() {
		try {
			while (!isInterrupted()) {
				String message = getNextMessageFromQueue();
				sendMessageToClient(message);
			}
		} catch (Exception e) {
		}

		clientInfo.clientListener.interrupt();
		serverDispatcher.removeClient(clientInfo);
	}

}