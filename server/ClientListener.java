package sysats.server;

//protocol timestamp:username:message
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

import sysats.protocol.Protocol;

public class ClientListener extends Thread {
	private ServerDispatcher serverDispatcher;
	private ClientInfo clientInfo;
	private ObjectInputStream in;

	public ClientListener(ClientInfo clientInfo,
			ServerDispatcher serverDispatcher) throws IOException {
		this.clientInfo = clientInfo;
		this.serverDispatcher = serverDispatcher;
		Socket socket = clientInfo.clientSocket;
		this.in = new ObjectInputStream(socket.getInputStream());
	}

	public void run() {
		System.out.println("Logged in.");
		while (!isInterrupted()) {
			//pridedam timestampa, kai ateina is serverio zinute
			Object object = null;
			try {
				object = in.readObject();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
			if (object == null)
				break;
			if (!(object instanceof Protocol))
				continue;
			Protocol protocol = (Protocol)object;
			protocol.updateTime();
			handleProtocol(protocol);
		}
		clientInfo.clientSender.interrupt();
		serverDispatcher.removeClient(clientInfo);
		System.out.println("Logged out.");
	}

	private void handleProtocol(Protocol protocol) {
		int type = protocol.getType();
		serverDispatcher.dispatchMessage(clientInfo, protocol);
	}
}
