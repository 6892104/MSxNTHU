package item;

import java.util.Random;

public class Money{
	
	private int x, y;
	private int width, height;
	private int amount;
	
	public Money(int amount, int x, int y){
		this.amount = setRandom(amount);
		this.x = x;
		this.y = y;
		width = 30;
		height = 30;
		if(this.amount > 100){
			width = 50;
			height = 50;
		}
		/*if(this.amount > 1000){
			width = 60;
			height = 60;
		}*/
	}
	
	private int setRandom(int value) {
		Random r = new Random();
		int i = r.nextInt(21) -10;
		return value + value*i/100;
	}
	
	public int amount(){
		return amount;
	}
	
	public int width(){
    	return width;
    }
    
    public int height(){
    	return height;
    }
    
    public int x(){
    	return x;
    }
    
    public int y(){
    	return y;
    }
}
