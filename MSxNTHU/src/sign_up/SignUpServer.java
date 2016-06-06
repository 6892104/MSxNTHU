package sign_up;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class SignUpServer extends JFrame{

	private ServerSocket serverSocket;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	private User user;
	private JTextArea display;
	
	
	public SignUpServer(int portNum) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setPreferredSize(new Dimension(300,200));
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setLocation(300, 100);
		
		display = new JTextArea();
		display.setPreferredSize(new Dimension(300,200));
		display.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(this.display);
		this.add(scrollPane);
		
		this.pack();
		this.setVisible(true);
		try {
//			connections.clear();
			user = new User();
			this.serverSocket = new ServerSocket(portNum);
		display.append("SignUpServer starts listening on port " + portNum + ".\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runForever() {
		display.append("SignUpServer starts waiting for client.\n");
		while(true) {
			try {
				Socket connectionToClient = this.serverSocket.accept();
				display.append("Get connection from client "
									+ connectionToClient.getInetAddress() + ":"
									+ connectionToClient.getPort() + "\n");
				ConnectionThread connThread = new ConnectionThread(connectionToClient);
				connThread.start();
				this.connections.add(connThread);
			} catch (BindException e){
				e.printStackTrace();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	class ConnectionThread extends Thread {
		private Socket socket;
		private BufferedReader reader;
		private PrintWriter writer;

		public ConnectionThread(Socket socket) {
			this.socket = socket;
			try {
				this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		public void run() {
			String action;
			try {
				action = this.reader.readLine();
				if(action.equals("check")) {
					String inputAccount = this.reader.readLine();
					String inputPassword = this.reader.readLine();
					if(user.checkID(inputAccount, inputPassword)) {
						this.sendMessage("succeed");
						user.sendInfo(this, inputAccount);
					}
					else {
						this.sendMessage("fail");
					}
					closeSocket();
				}
				else if(action.equals("write")) {
					String inputAccount = reader.readLine();
					String info = reader.readLine();
					while(info != null) {
						info += "\n";
						info += reader.readLine();
					}
					info += "\n";
					user.writeInfo(inputAccount, info);
					closeSocket();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public void sendMessage(String message) {
			this.writer.println(message);
			this.writer.flush();
		}
		public void closeSocket() {
			try {
				sendMessage("Close");
				socket.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		SignUpServer server = new SignUpServer(6687);
		server.runForever();
	}
}
