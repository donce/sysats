package sysats.client.gui;

import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import sysats.protocol.FileProtocol;
import sysats.protocol.MessageProtocol;
import sysats.protocol.Protocol;

public class MainWindow extends javax.swing.JFrame {
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextPane jTextPane2;
	private ObjectOutputStream out = null;
	private String username = "anon";
	
	private final byte FILE_SIGNATURE = 7;
	
	private Action enterAction = new AbstractAction() {
		public void actionPerformed(ActionEvent tf) {
			sendCurrentMessage();
		}
	};

	public MainWindow(ObjectOutputStream out) {
		this.out = out;
		this.setTitle("Sysats");
		this.setResizable(false);
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {

		}
		this.setVisible(true);
		initComponents();
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jButton1 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextPane2 = new javax.swing.JTextPane();// conversation langas
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jTextField1 = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jScrollPane1.setViewportView(jTextArea1);

		jButton1.setText("Siusti");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});
		jTextPane2.setEditable(false);
		jScrollPane2.setViewportView(jTextPane2);

		jTextArea1.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),

		"doEnterAction");

		jTextArea1.getActionMap().put("doEnterAction", enterAction);

		jButton2.setText("Keisti naudotojo varda");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Atsijungti");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jTextField1.setText("anon");
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jLabel2.setText("Naudotojo vardas");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jScrollPane2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														588, Short.MAX_VALUE)
												.addComponent(jScrollPane1))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jButton1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jButton2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jTextField1)
												.addComponent(
														jLabel2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(
										layout.createSequentialGroup()
												.addGap(618, 618, 618)
												.addComponent(
														jButton3,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														131, Short.MAX_VALUE)
												.addContainerGap())));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(20, 20, 20)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING,
												false)
												.addComponent(
														jScrollPane2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														457,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(297,
																		297,
																		297)
																.addComponent(
																		jLabel2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		47,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		jTextField1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		31,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18,
																		18)
																.addComponent(
																		jButton2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		57,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										27, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING,
												false)
												.addComponent(
														jButton1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														80, Short.MAX_VALUE)
												.addComponent(jScrollPane1))
								.addGap(22, 22, 22))
				.addGroup(
						layout.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(
										layout.createSequentialGroup()
												.addGap(30, 30, 30)
												.addComponent(
														jButton3,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														57,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addContainerGap(519,
														Short.MAX_VALUE))));

		pack();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		sendCurrentMessage();
	}
	
	private void sendCurrentMessage() {
		String message = jTextArea1.getText();
		jTextArea1.setText("");
		if (message.length() > 0)
			sendMessage(message);
	}

	private void showSendFileDialog() {
		JFileChooser fc = new JFileChooser();
		fc.setApproveButtonText("Siųsti");
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			sendFile(file);
		}
	}
	
	private void sendProtocol(Protocol protocol) {
		try {
			out.writeObject(protocol);
			out.flush();
		}
		catch (IOException e) {
		}
	}
	
	private void sendMessage(String message) {
		System.out.println(message);
		sendProtocol(new MessageProtocol(this.getUsername(), message));
	}
	
	private void sendFile(File file) {
		InputStream input;
		try {
			input = new BufferedInputStream(new FileInputStream(file));
		}
		catch (FileNotFoundException e) {
			return;
		}

		byte[] data = new byte[(int)file.length()];
		try {
			input.read(data);
		}
		catch (IOException e) {
			return;
		}
		sendProtocol(new FileProtocol(this.getUsername(), file.getName(), data));
	}
	
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// keisti varda listeneris
		String temp = jTextField1.getText();
		if (temp != null) {
			setUsername(temp);
		}
	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// atsijungti listeneris
//		System.exit(-1);
		showSendFileDialog();
	}

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public synchronized void updateTextPane(MessageProtocol protocol) {
		System.out.println("Zinute: " + protocol.getMessage());
		Document doc = jTextPane2.getDocument();
		try {
			doc.insertString(doc.getLength(), protocol.getUsername() + "<"
					+ protocol.getTime() + "> : " + protocol.getMessage()
					+ "\n", null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

}
