package main.java;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;


import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private String name;
	private int radius = 20;	//37 character in total
	private int iniX, iniY;
	public float x, y;
	private Color color;
	private HashMap<Character,Integer> interact = new HashMap<Character,Integer>();
	private ArrayList<Character> target = new ArrayList<Character>();

	public Character(MainApplet parent, String name, String color, int seq){

		this.parent = parent;
		this.name = name;
		this.color = hex2Rgb(color.substring(1));
		iniX = (radius+20)+(seq/10)*(2*radius+20);
		iniY = 25+(seq%10)*(2*radius+20);
		x = iniX;
		y = iniY;
	}

	public void display(){
		parent.fill(this.color.getRGB());
		parent.ellipse(this.x, this.y, radius, radius);
	}
	
	public static Color hex2Rgb(String colorStr) {
		//"# FF_808_080"
	    return new Color(
	            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
	            Integer.valueOf( colorStr.substring( 3, 6 ), 16 ),
	            Integer.valueOf( colorStr.substring( 6, 9 ), 16 ) );
	}
	
	public void addTarget(Character ch, int level){
		target.add(ch);
		interact.put(ch,level);
	}
	
	public ArrayList<Character> getTarget(){
		return target;
	}
	
	public HashMap<Character,Integer> getInteract(){
		return interact;
	}
}
