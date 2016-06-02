package role;

import java.awt.Image;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

public class Task {
	String name;
	public Task(String name){
		this.name = name;
		loadData();
	}
	
	private void loadData(){
		JSONObject data;
		JSONArray data_array;
		try{
			String file = "message/" + name + ".json";
			data = new PApplet().loadJSONObject(file);
		
			data_array = data.getJSONArray("messages");
			for(int i =0; i< data_array.size(); i++) {
				String dialog = data_array.getJSONObject(i).getString("dialog");
				String type = data_array.getJSONObject(i).getString("type");
				String message = data_array.getJSONObject(i).getString("message");
				if(dialog.equals("message")){
					JOptionPane.showMessageDialog(null, message, name+" :", getPicture(type) );
				}else if(dialog.equals("confirm")){
					int option = JOptionPane.showConfirmDialog(null, message, name+" :", JOptionPane.YES_NO_OPTION, getPicture(type));
					if(option == JOptionPane.YES_OPTION){
						String yes = data_array.getJSONObject(i).getString("yes");
						if(yes.equals("task")){
							int number = data_array.getJSONObject(i).getInt("task_number");
							JSONArray tasks = data.getJSONArray("tasks");
							doTask(tasks.getJSONObject(number));
						}
					}
				}
			}
		
		}catch (NullPointerException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "載入" + name + "對話錯誤");
		}
	}
	
	private void doTask(JSONObject task){
		if(task.getString("type").equals("collect")){
			String item = task.getString("item");
			int number = task.getInt("number");
			int option = JOptionPane.showConfirmDialog(null, "幫我收集 "+item+" " +number+" 個好嗎？", name+" :", JOptionPane.YES_NO_OPTION, getPicture("question"));
			/*if(option == JOptionPane.YES_OPTION){
				String yes = data_array.getJSONObject(i).getString("yes");
				if(yes.equals("task")){
					int number = data_array.getJSONObject(i).getInt("task_number");
					JSONArray tasks = data.getJSONArray("tasks");
					doTask(tasks.getJSONObject(number));
				}
			}*/
		}
	}
	
	private int getPicture(String type){
		if(type.equals("information"))
			return JOptionPane.INFORMATION_MESSAGE;
		else if(type.equals("question"))
			return JOptionPane.QUESTION_MESSAGE;
		else if(type.equals("warning"))
			return JOptionPane.WARNING_MESSAGE;
		else if(type.equals("error"))
			return JOptionPane.ERROR_MESSAGE;
		else
			return JOptionPane.PLAIN_MESSAGE;
	}
}
