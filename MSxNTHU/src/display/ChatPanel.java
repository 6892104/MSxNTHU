package display;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.ChatClient;
import role.Beginner;

public class ChatPanel extends JPanel{
	private DisplayPanel display;
	private ChatClient client;
	private JTextArea chatDisplay;
	private JTextField writeBoard;
	
	public ChatPanel(DisplayPanel display){
		this.display = display;
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(400, 200);
		chatDisplay = new JTextArea()
		{
		    @Override
		    protected void paintComponent(Graphics g) {
		        try {
		            Composite composite = ((Graphics2D)g).getComposite();

		            ((Graphics2D)g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		            g.setColor(getBackground());
		            g.fillRect(0, 0, getWidth(), getHeight());

		            ((Graphics2D)g).setComposite(composite);
		            paintChildren(g);
		            super.paintComponent(g);
		        }
		        catch(IndexOutOfBoundsException e) {
		            super.paintComponent(g);
		        }
		    }       
		};
		chatDisplay.setEditable(false);
		chatDisplay.setFocusable(false);
		chatDisplay.setOpaque(false);
		//chatDisplay.setBackground(Color.blue);
		this.add(chatDisplay);
		chatDisplay.setBounds(0, 0, this.getWidth(), this.getHeight() - 20);
		//chatDisplay.setBackground(Color.BLACK);
		writeBoard = new JTextField();
		writeBoard.setFocusable(true);
		this.add(writeBoard);
		writeBoard.setBounds(0, this.getHeight() - 20, this.getWidth(), 20);
		
		client = new ChatClient("127.0.0.1", 8000, this);
		client.connect();
		
		writeBoard.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(writeBoard.getText().length() > 0){
						client.sendMessage(display.getCharacterName() + " : " + writeBoard.getText());
						writeBoard.setText("");
						display.requestFocus();
						writeBoard.transferFocus();
					}
					e.consume();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {	
			}
		});
	}
	
	public void getKey(){
		writeBoard.requestFocus();
	}
	
	public void displayMessage(String message){
		chatDisplay.append(message + "\n");
	}
	
	public void closeConnection(){
		client.closeConnection();
	}
}
