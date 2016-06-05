package questionSystem;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import display.DisplayPanel;

public class QuestionPanel extends JPanel
{
	private DisplayPanel display;
	private SurveyNPC npc;
	private Image image;
	private JTextArea writeBoard;
	private BufferedWriter writer;
	private JButton enterButton, finishButton;
	private QuestionPanel temp;
	
	public QuestionPanel(DisplayPanel display, SurveyNPC npc)
	{
		
		temp = this;
		this.display = display;
		this.npc = npc;
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(534, 373);
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream("/question.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			writer = new BufferedWriter(new FileWriter("./userQuestion.txt"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		writeBoard = new JTextArea();
		writeBoard.setLineWrap(true);
		writeBoard.setBounds(40, 80, 340, 250);
		writeBoard.addKeyListener(null);
		this.add(writeBoard);
		enterButton = new JButton();
		enterButton.setBounds(380, 310, 60, 30);
		enterButton.setText("¿é¤J");
		enterButton.addMouseListener(new MouseAdapter(){
	        public void mousePressed(MouseEvent e){
	        	try {
					writer.write(writeBoard.getText());
					writer.newLine();
					writer.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        	
	            writeBoard.setText("");
	        }
	    });
		this.add(enterButton);
		finishButton = new JButton();
		finishButton.setBounds(450, 310, 60, 30);
		finishButton.setText("µ²§ô");
		finishButton.addMouseListener(new MouseAdapter(){
	        public void mousePressed(MouseEvent e){
	        	temp.setVisible(false);
	        }
	    });
		this.add(finishButton);
	}
	
	protected void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, 534, 373, null);
	}
	
}
