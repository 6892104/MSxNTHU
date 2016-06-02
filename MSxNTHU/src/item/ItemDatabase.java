package item;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.HashMap;
import java.util.Random;

//@SuppressWarnings("serial")
public class ItemDatabase {
	JSONObject data;
	JSONArray data_array;
	private HashMap<String, Consumable> consumables;
	private HashMap<String, Equipment> equipments;
	private HashMap<String, OtherItem> otherItems;


	public ItemDatabase() {
		consumables = new HashMap<String, Consumable>();
		equipments = new HashMap<String, Equipment>();
		otherItems = new HashMap<String, OtherItem>();
		loadData();
	}
	public void loadData() {
//		data = loadJSONObject(file);
		try{
			String file1 = "item/item_data.json";
//			String file2 = this.getClass().getResource("/item/item_data.json").getFile();
			data = new PApplet().loadJSONObject(file1);
		}catch (NullPointerException ie){
			javax.swing.JOptionPane.showMessageDialog(null, "¸ü¤Jª««~data¿ù»~");
		}

		data_array = data.getJSONArray("Consumables");
		for(int i =0; i< data_array.size(); i++) {
			String name = data_array.getJSONObject(i).getString("name");
			int lvRequired = data_array.getJSONObject(i).getInt("lvRequired");
//			int atk = data_array.getJSONObject(i).getInt("atk");
//			int matk = data_array.getJSONObject(i).getInt("matk");
//			int def = data_array.getJSONObject(i).getInt("def");
//			int mdef = data_array.getJSONObject(i).getInt("mdef");
			int price = data_array.getJSONObject(i).getInt("price");
			int probability = data_array.getJSONObject(i).getInt("probability");
			int hp =data_array.getJSONObject(i).getInt("HP"); 
			int mp =data_array.getJSONObject(i).getInt("MP");
			int maxNum =data_array.getJSONObject(i).getInt("maxNum"); 

			Consumable c = new Consumable(name, lvRequired, price, probability, hp, mp, maxNum);
			consumables.put(name, c);
			//System.out.println(c.name);
		}
		
		data_array = data.getJSONArray("Equipments");
		for(int i =0; i< data_array.size(); i++) {
			String name = data_array.getJSONObject(i).getString("name");
			int lvRequired = data_array.getJSONObject(i).getInt("lvRequired");
			int atk = data_array.getJSONObject(i).getInt("atk");
			int matk = data_array.getJSONObject(i).getInt("matk");
			int def = data_array.getJSONObject(i).getInt("def");
			int mdef = data_array.getJSONObject(i).getInt("mdef");
			int price = data_array.getJSONObject(i).getInt("price");
			int probability = data_array.getJSONObject(i).getInt("probability");
						
			Equipment e = new Equipment(name, lvRequired, atk, matk, def, mdef, price, probability);
			equipments.put(name,  e);
		}
		data_array = data.getJSONArray("Other Items");
		for(int i =0; i< data_array.size(); i++) {
			String name = data_array.getJSONObject(i).getString("name");
			int lvRequired = data_array.getJSONObject(i).getInt("lvRequired");
			int price = data_array.getJSONObject(i).getInt("price");
			int probability = data_array.getJSONObject(i).getInt("probability");
			int maxNum =data_array.getJSONObject(i).getInt("maxNum");
			System.out.println(name + " " + maxNum);
			OtherItem o = new OtherItem(name, lvRequired, price, probability, maxNum);
			otherItems.put(name,  o);
		}

	}
	
	public Item createItem(String itemName) {
		if(consumables.containsKey(itemName)) {
			//System.out.println("hi");
			if(isHit(consumables.get(itemName).probability)) {
				Consumable i = new Consumable(consumables.get(itemName));
				return i;
			}
			else return null;
		}
		else if(equipments.containsKey(itemName)) {
			if(isHit(equipments.get(itemName).probability)) {
				Equipment i = new Equipment(equipments.get(itemName));
				return i;
			}
			else return null;
		}
		else if(otherItems.containsKey(itemName)) {
			if(isHit(otherItems.get(itemName).probability)) {
				Item i = new OtherItem(otherItems.get(itemName));
				return i;
			}
			else return null;
		}
		return null;
}

	private boolean isHit(int probability) {
		Random r = new Random();
		int num = r.nextInt(100);
		return (num < probability) ? true : false;
	}
}