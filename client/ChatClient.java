package sysats.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import sysats.client.gui.MainWindow;

public class ChatClient {
	public static final String HOSTNAME = "localhost";
	public static final int PORT = 1234;
	private MainWindow mw;
	private volatile String username;
	private BufferedReader in = null;
	private PrintWriter out = null;

	public void start() {

		try {
			Socket socket = new Socket(HOSTNAME, PORT);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			System.out.println("Connected to server " + HOSTNAME + ":" + PORT);
		} catch (IOException ioe) {
			System.err.println("Can not establish connection to " + HOSTNAME
					+ ":" + PORT);
			ioe.printStackTrace();
			System.exit(-1);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				mw = new MainWindow(out);
			}
		});

		try {
			String message;
			while ((message = in.readLine()) != null) {// skaitom zinutes is
														// serverio
				System.out.println(message);
				mw.updateTextPane(message);
			}
		} catch (IOException e) {
			System.out.println("Connection error");
		}
	}

	public synchronized void setUsername(String username) {
		this.username = username;
	}

	public synchronized String getUsername() {
		return this.username;
	}
}