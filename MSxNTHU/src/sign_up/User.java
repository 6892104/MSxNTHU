package sign_up;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import sign_up.SignUpServer.ConnectionThread;

public class User {
	private BufferedReader reader;
	private BufferedWriter writer;
	public HashMap<String, String> users;
	
	public User() {
		try {
			reader = new BufferedReader(new FileReader(new File("resource/user_list.txt")));
			users = new HashMap<String, String>();
			String userAccount = "";
			String userPassword = "";
			
			userAccount = reader.readLine();
			while(userAccount != null) {
				userPassword = reader.readLine();
				users.put(userAccount,  userPassword);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean checkID(String inputAccount, String inputPassword) {
		if(users.containsKey(inputAccount)) {
			if(users.get(inputAccount).equals(inputPassword)) {
				return true;
			}
		}
		return false;
	}
	
	public void sendInfo(ConnectionThread connect, String userAccount) {
		try {
			reader = new BufferedReader(new FileReader(new File("resource/" + userAccount + ".txt")));
			String line = reader.readLine();
			while(line != null) {
				connect.sendMessage(line);
				line = reader.readLine();
			}
			connect.sendMessage("completed");
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
