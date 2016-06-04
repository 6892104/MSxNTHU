package questionSystem;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.ChatClient;
import display.DisplayPanel;

public class QuestionPanel extends JPanel
{
	private DisplayPanel display;
	private Image image;
	private JTextField writeBoard;
	
	public QuestionPanel(DisplayPanel display)
	{
		this.display = display;
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(534, 373);
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream("/question.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		writeBoard = new JTextField();
		//writeBoard.setBounds(this.getWidth(), this.getHeight(), width, height);
	}
	
	protected void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, 534, 373, null);
	}
	
}
