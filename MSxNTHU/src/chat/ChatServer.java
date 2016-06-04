package chat;

import java.awt.Dimension;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import item.Money;

public class ChatServer extends JFrame{

	private ServerSocket serverSocket;
	private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
	ConnectionThread waiting;
	private JTextArea display;
	
	public ChatServer(int portNum) {
		//initialize
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
		/*typing.start();*/
		
		try {
			this.serverSocket = new ServerSocket(portNum);
			display.append("Server starts listening on port " + portNum + ".\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void runForever() {
		display.append("Server starts waiting for client.\n");
		// Create a loop to make server wait for client forever (unless you stop it)
		// Make sure you do create a connectionThread and add it into 'connections'
		while(true) {
			try {
				 Socket connectionToClient = this.serverSocket.accept();
				 display.append("Get connection from client "
					 + connectionToClient.getInetAddress() + " : "
					 + connectionToClient.getPort() + "\n");
			 // new served client thread start
				 ConnectionThread connThread = new ConnectionThread(connectionToClient);
				 connThread.setParent(this);
				 connThread.start();
			 // add the connection thread to a ArrayList, so that we can access it afteresrd.
			 this.connections.add(connThread);
			 
			 /*if(connections.size() % 2 == 0){
				 if(waiting != null){
					 display.append("Game" + games.size() + " Start!\n");
					 games.add(new GameThread(waiting, connThread, this));
				 }
			 }else
				 waiting = connThread;*/

			} catch (BindException e){
			 e.printStackTrace();
			} catch (IOException e){
			 e.printStackTrace();
			}
		}
	}
	
	public void broadcast(String message){
		display.append("" + message + "\n");
		Iterator<ConnectionThread> cons = connections.iterator();
		while(cons.hasNext()){
			ConnectionThread connection = cons.next();
			if(connection.isClose()){
				cons.remove();
			}else{
				connection.sendMessage(message);
			}
		}
	}
	
	public static void main(String[] args) {
		
		ChatServer server = new ChatServer(8000);
		server.runForever();

	}

}
