package sign_up;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.MouseInfo;

public class SignUpPanel extends JPanel{

	private BufferedImage image,mouse;
	private JLabel name;
	private SignUpPanel frame;
	private JTextField account,password;//如果型態為TextField，就不能用設定邊界顏色
	private JButton signup;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	private static final long serialVersionUID = 1L;
	
	public SignUpPanel(SignUpClient f){
		//frame = f;//傳入gamestage，用來把建好的物件加入jframe
		
		setLayout(null);
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		account = new JTextField();//玩家輸入文字的地方
		account.setBounds(440, 233, 150, 23);//設定大小
		account.setFont(font1);
		
		password = new JTextField();//玩家輸入文字的地方
		password.setBounds(440, 263, 150, 23);//設定大小
		password.setBackground(Color.decode("#A44600"));
		account.setBackground(Color.decode("#A44600"));
		password.setFont(font1);
		account.setBorder(null);
		password.setBorder(null);
		
		
	    signup = new JButton("");
	    add(signup);
		signup.setBounds(596, 237, 84, 41);
		signup.setContentAreaFilled(false);
		signup.setFocusable(false);
		//signup.setBorderPainted(false);
	    signup.addActionListener(new ActionListener() {          
	        public void actionPerformed(ActionEvent e) {
	        	//SignUpMain.this.sendMessage( mp.account.getText()  + mp.password.getText());
	       }
	   });
		
		
		try
		{ 
				mouse = ImageIO.read(this.getClass().getResourceAsStream("/mouse.png")); //載入圖片
                image=ImageIO.read(this.getClass().getResourceAsStream("/signup_image.png")); //載入圖片背景
                //Cursor cr = Toolkit.getDefaultToolkit().createCustomCursor( mouse , new Point(0,0) ,"MyCursor" );
                Cursor cr = toolkit.createCustomCursor( mouse , new Point(0,0) ,"MyCursor" );
                toolkit.getBestCursorSize(32, 32);
        		frame.setCursor( cr );
        }
		catch(Exception e)
		{ 
                javax.swing.JOptionPane.showMessageDialog(null, "載入圖檔錯誤:"); 
        }
		
		this.add(account);
		this.add(password);
	}
	@Override
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //setBackground(new Color(141, 216, 252));//設定背景顏色  
        g.drawImage(image,0,0,image.getWidth(),image.getHeight(),null);//畫出背景圖片
    }

}

