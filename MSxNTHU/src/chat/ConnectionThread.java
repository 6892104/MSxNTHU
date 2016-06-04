package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


//Define an inner class (class name should be ConnectionThread)
class ConnectionThread extends Thread {
	private Socket socket;
	private BufferedReader reader;
	private PrintWriter writer;
	private ChatServer parent;
	
	public ConnectionThread(Socket socket) {
		 this.socket = socket;
		 try {
			 this.reader = new BufferedReader(new
					 InputStreamReader(socket.getInputStream()));
			 this.writer = new PrintWriter(new
					 OutputStreamWriter(socket.getOutputStream()));
		 } catch (IOException e){
			 e.printStackTrace();
		 }
	}
	public void run() {
		 while(true) {
			 try {
				 String line = this.reader.readLine();
				 //System.out.println(line); // line is message from server
				 if(line.equals("Close")){
					 socket.close();
					 break;
				 }
				 parent.broadcast(line);
			 } catch (IOException e){
				 e.printStackTrace();
			 }
		 }
	}
	
	public void sendMessage(String message) {
		 this.writer.println(message);
		 this.writer.flush();
	}
	
	public void setParent(ChatServer parent){
		this.parent = parent;
	}
	
	public boolean isClose(){
		return socket.isClosed();
	}
	
	/*private void readCommand(String message){
		if(message.substring(0,6).equals("Answer")){
			parent.checkAnswer(this, message.substring(7));
		}
	}*/
}
