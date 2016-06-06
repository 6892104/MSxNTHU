package MapleStory;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.List;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import display.DisplayPanel;
import processing.core.PApplet;
import sign_up.SignUpClient;
import sign_up.SignUpPanel;

public class MainWindow extends JFrame {
	
	/*private slots:
	    void handleButton();
	    void back_to_menu();
	    void gameStart();
	    void keyaction();
	    void tcreator(int,int,std::string);
    void deadinfor(int);
	    void deadanima();
	    void green_mode();*/
	//private ModForGame gameMod;
	//private Menu *menu;
	private Control control;
	private KeyControl keyControl;

	
	private DisplayPanel display;
	public String account;

	    /*enum Monster{pig,green};
	    Monster which;
	    Pig * piggy[20];
	    int pig_num;
	    Green * greens[20];
	    int green_num;

	    Status *status;
	    QPushButton * button , *button2 , *deadbutton;
	    std::vector <Item *> treasure;
	    Item * item[9];
	    Bag *bag;
	    int have_item[9];
	    std::string str[9];
	    std::map <std::string,bool> key;

	    easyMusic *start_bgm;

	    easyMusic *drop,*picks,*tombfall;*/
	    //add the sound effect here
	Minim minim;
	AudioPlayer startBGM;
	
	public boolean soundOn;
	
	
	public MainWindow(){
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(null);
		this.getContentPane().setPreferredSize(new Dimension(1280, 720)); //inner space
	   /* this.setPreferredSize(new Dimension(1280, 720));
	    this.setMinimumSize(new Dimension(1280, 720));*/
	    this.setSize(1280, 720);
		this.setLocation(300, 100);
		//read picture
		try {
			Image backGround = ImageIO.read(this.getClass().getResourceAsStream("/attack.0.png"));
			this.setIconImage(backGround);
		}catch (Exception ie){
			javax.swing.JOptionPane.showMessageDialog(null, "���J���ɿ��~");
		}
	    this.setTitle("MapleStory");
	    this.addWindowListener(new java.awt.event.WindowAdapter() {
	        @Override
	        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	            if (JOptionPane.showConfirmDialog(null, 
	                "Are you sure to close this window?", "Really Closing?", 
	                JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
	            	closeGame();
	            }
	        }
	    });
	    
	    gameMenu();
	    
	    //this.setUndecorated(true); //no border
	    //this.setExtendedState(JFrame.MAXIMIZED_BOTH); //full screen
	    //this.setResizable(false);
	    //this.pack();
	    //KeyHook.blockWindowsKey();
	}

	private void gameMenu(){
		SignUpPanel signPanel = new SignUpPanel(this);
		this.add(signPanel);
		this.getContentPane().setPreferredSize(new Dimension(signPanel.getWidth(), signPanel.getHeight()));
		this.pack();
	    this.setVisible(true);
	    signPanel.repaint();
	    //System.out.println(signPanel.getWidth() + " "+ signPanel.getHeight());
	}
	
	public void checkAccount(String account, String password){
		SignUpClient client = new SignUpClient("127.0.0.1", 6687, this);
		client.connect();
		client.sendMessage("check");
		client.sendMessage(account);
		client.sendMessage(password);
		//client.downloading = true;
		this.account = account;
	}
	
	public void gameStart()
	{
		this.getContentPane().removeAll();
		this.getContentPane().setPreferredSize(new Dimension(1280, 720));
		display = new DisplayPanel();
	    this.add(display);
	    keyControl = new KeyControl();
	    display.addKeyListener(keyControl);
	    this.pack();
	    this.setVisible(true);
	    display.setBounds(0, 0, this.getContentPane().getWidth(), this.getContentPane().getHeight());
	//System.out.println("ass " + this.getContentPane().getWidth() + " "+ this.getContentPane().getHeight());
	    //which = pig;
	
		minim = new Minim(new PApplet());
		soundOn = true;
		startBGM = minim.loadFile(this.getClass().getResource("/bgm.mp3").getPath());
	    //menu->hide();
	    //gameMod = StartMod;
	    //if(play_bgm) start_bgm->play();
		if(soundOn) startBGM.loop();
	    
	    control = new Control(display);
	    loadData();
	    control.setKeyControl(keyControl);
	    control.start();
	}
	
	private void loadData(){
		File file = null;
		try{
			String data;
			file = new File("./user.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
				data = reader.readLine();
			String name = data;
				data = reader.readLine();
			String newMap = data;
				data = reader.readLine();
			int lv = Integer.valueOf(data);
				data = reader.readLine();
			int exp = Integer.valueOf(data);
				data = reader.readLine();
			int hp = Integer.valueOf(data);
				data = reader.readLine();
			int max_hp = Integer.valueOf(data);
				data = reader.readLine();
			int mp = Integer.valueOf(data);
				data = reader.readLine();
			int max_mp = Integer.valueOf(data);
				data = reader.readLine();
			int atk = Integer.valueOf(data);
				data = reader.readLine();
			int matk = Integer.valueOf(data);
				data = reader.readLine();
			int def = Integer.valueOf(data);
				data = reader.readLine();
			int mdef = Integer.valueOf(data);
			
			control.setData(name, newMap, lv, exp, hp, max_hp, mp, max_mp, atk, matk, def, mdef);
			
				data = reader.readLine();
			int money = Integer.valueOf(data);
			control.setBagMoney(money);
				data = reader.readLine();
			while(!data.equals("done")){
				String item = data;
					data = reader.readLine();
				int number = Integer.valueOf(data);
				control.setBagItem(item, number);
					data = reader.readLine();
			}
			reader.close();
			file.delete();
			//String map = data;
		}catch(IOException e){
			e.printStackTrace();
			if(file != null)
				file.delete();
		}
	}
	
	private void closeGame(){
		if(display != null)
			display.closeConnection();
		if(control != null)
			control.closeGame(account);
        System.exit(0);
	}
	
	public static void main(String[] args) {
		new MainWindow();
	}

}
