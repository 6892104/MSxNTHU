package questionSystem;

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

public class QuestionServer extends JFrame{

    private ServerSocket serverSocket;
    private List<ConnectionThread> connections = new ArrayList<ConnectionThread>();
    private QuestionOperator words;
    private JTextArea display;
    
    
    public QuestionServer(int portNum) {
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
//            connections.clear();
            words = new QuestionOperator();
            this.serverSocket = new ServerSocket(portNum);
        display.append("Server starts listening on port " + portNum + ".\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void runForever() {
        display.append("Server starts waiting for client.\n");
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

    public void sendQuestion(ConnectionThread c, String fileName) {
        String question = words.getQuestion(fileName);
        c.sendMessage(question);
    }
    
    class ConnectionThread extends Thread {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;
        private String fileName;
        private int qNum, totalNum;
        private boolean hasInput;

        public ConnectionThread(Socket socket) {
            this.socket = socket;
            try {
                this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                this.writer = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()));
                this.qNum = 1;
                this.totalNum = words.questions.size();
                this.hasInput = false;
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        public void run() {
            while(qNum <= totalNum) {
                try {
                    fileName = "question" + qNum;
                    sendQuestion(this, fileName);
                    String inputAns = this.reader.readLine();
                    if(inputAns != null) {
                        words.writeAnswer(fileName, inputAns);

                        qNum++;
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            try {
            	sendMessage("Close");
                socket.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void sendMessage(String message) {
            this.writer.println(message);
            this.writer.flush();
        }
    }

    public static void main(String[] args) {
        QuestionServer server = new QuestionServer(6000);
        server.runForever();
    }
}
