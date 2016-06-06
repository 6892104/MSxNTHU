package sign_up;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import display.ChatPanel;


public class SignUpClient {

	private String destinationIPAddr;
	private int destinationPortNum;
	private Socket socket;
	private PrintWriter writer;
	private ClientThread connection;
	//private SignUpPanel signPanel;
	
	public boolean downloading;
	
	
	public SignUpClient(String IPAddress, int portNum) {
		this.destinationIPAddr = IPAddress;
		this.destinationPortNum = portNum;
		//this.signPanel = signPanel;
		downloading = false;
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
	
	private void readCommand(String message) {
		 if(message.equals("succeed")){
			 downloading = true;
		 }else if(downloading){
			 writeFile(message);
		 }
	}
	
	private void writeFile(String data){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter("./user.txt", true));
			writer.write(data + "\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
					 System.out.println(line);
					 if(line.equals("Close")){
						 socket.close();
						 break;
					 }else if(line.equals("completed")){
						 socket.close();
						 break;
					 }else 
						 readCommand(line);
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
				connection.reader.close();
				socket.close();
			}
		}catch(IOException e){
			javax.swing.JOptionPane.showMessageDialog(null, "Ãö³¬socket¿ù»~");
			e.printStackTrace();
		}
	}

	
	/*public static void main(String[] args) {
		
		GameClient client = new GameClient();
		client.setIPAddress("127.0.0.1").setPort(8000).connect();
		
		Equivalent of the above
		// ChatClient client = new ChatClient("127.0.0.1", 8000);
		// client.connect();
	}*/

}
