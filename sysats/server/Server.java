package sysats.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static final int LISTENING_PORT = 1234;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
			System.out.println("ChatServer started on port " + LISTENING_PORT);
		} catch (IOException se) {
			System.err
					.println("Can not start server on port " + LISTENING_PORT);
			System.exit(-1);
		}

		ServerDispatcher serverDispatcher = new ServerDispatcher();
		serverDispatcher.start();

		try {
			while (true) {
				try {
					Socket socket = serverSocket.accept();// priimam connection
															// jei kas nors
															// jungiasi,
															// priesingu
															// atveju blockina
					ClientInfo clientInfo = new ClientInfo();
					clientInfo.clientSocket = socket;// isimenam socketa
					ClientListener clientListener = new ClientListener(
							clientInfo, serverDispatcher);// sukuriam client
															// listener kuris
															// laukia zinuciu is
															// kliento ir
															// forwardina jas
															// server
															// dispatcheriui
					ClientSender clientSender = new ClientSender(clientInfo,
							serverDispatcher);// siuncia zinutes klientui nuo
												// server dispatcherio
					clientInfo.clientListener = clientListener;
					clientInfo.clientSender = clientSender;
					clientListener.start();
					clientSender.start();
					serverDispatcher.addClient(clientInfo);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
