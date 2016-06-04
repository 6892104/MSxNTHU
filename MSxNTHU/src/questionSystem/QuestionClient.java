package questionSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import processing.data.JSONArray;


public class QuestionClient {

		private String destinationIPAddr;
		private int destinationPortNum;
		private Socket socket;
		private PrintWriter writer;
		private ClientThread connection;
		//private ChatPanel chatPanel;
		
		
		public QuestionClient(String IPAddress, int portNum) {
			this.destinationIPAddr = IPAddress;
			this.destinationPortNum = portNum;
			//this.chatPanel = chatPanel;
		}
		
		public void sendMessage(String message) {
			StringBuilder sBuilder = new StringBuilder(message);
			this.writer.println(sBuilder.toString());
			this.writer.flush();
		}
		
		public void connect() {
			// Create socket & thread, remember to start your thread
			try {
				this.socket = new Socket(this.destinationIPAddr, this.destinationPortNum);
				this.writer = new PrintWriter(new
						 		OutputStreamWriter(this.socket.getOutputStream()));
				BufferedReader reader = new BufferedReader(new
								InputStreamReader(this.socket.getInputStream()));
				this.connection = new ClientThread(reader);
				this.connection.start();

				} catch (UnknownHostException e){
				 e.printStackTrace();
				} catch (ConnectException e){
				 e.printStackTrace();
				} catch (IOException e){
				 e.printStackTrace();
			}
		}
		
		private void readCommand(final String message) {
			
		}
		
		// Define an inner class for handling message reading
		class ClientThread extends Thread {
			 private BufferedReader reader;
			 
			 public ClientThread(BufferedReader reader) {
				 this.reader = reader;
			 }
			 
			 public void run() {
				 while(!socket.isClosed()) {
					 try {
						 String line = this.reader.readLine();
						 //chatPanel.displayMessage(line);
						 //System.out.println(line);
						 javax.swing.JOptionPane.showMessageDialog(null, line);
					 } catch (IOException e){
						 e.printStackTrace();
					 }
				 }
			 }
		}
		
		public void closeConnection(){
			try{
				if(socket != null){
					sendMessage("Close");
					connection.reader.close();;
					socket.close();
				}
			}catch(IOException e){
				javax.swing.JOptionPane.showMessageDialog(null, "Ãö³¬socket¿ù»~");
				e.printStackTrace();
			}
		}

		
		public static void main(String[] args) {
			
			/*GameClient client = new GameClient();
			client.setIPAddress("127.0.0.1").setPort(8000).connect();*/
			
			//Equivalent of the above
			QuestionClient client = new QuestionClient("127.0.0.1", 6000);
			client.connect();
		}

}

