package sysats.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import sysats.client.gui.MainWindow;
import sysats.server.Protocol;

public class ChatClient {
	public static final String HOSTNAME = "localhost";
	public static final int PORT = 1234;
	private MainWindow mw;
	private volatile String username;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;

	public void start() {
		try {
			Socket socket;
			try {
				socket = new Socket(HOSTNAME, PORT);
			} catch (ConnectException e) {
				System.err.println("Error connecting");
				System.exit(-1);
				return;
			}
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			System.out.println("Connected to server " + HOSTNAME + ":" + PORT);
		} catch (IOException ioe) {
			System.err.println("Can not establish connection to " + HOSTNAME + ": " + PORT);
			ioe.printStackTrace();
			System.exit(-1);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				mw = new MainWindow(out);
			}
		});

		while (true) {
			Object object = null;
			
			try {
				object = in.readObject();
			} catch (IOException  e) {
				System.out.println("IOException");
			}
			catch (ClassNotFoundException e) {
				System.out.println("ClassNotFoundException");
			}
			
			if (object == null)
				break;
			if (!(object instanceof Protocol))
				continue;
			Protocol protocol = (Protocol)object;
			handleProtocol(protocol);
		}
	}
	
	void handleProtocol(Protocol protocol) {
		int type = protocol.getType();
		if (type == 1)
			mw.updateTextPane(protocol);
	}

	public synchronized void setUsername(String username) {
		this.username = username;
	}

	public synchronized String getUsername() {
		return this.username;
	}
}