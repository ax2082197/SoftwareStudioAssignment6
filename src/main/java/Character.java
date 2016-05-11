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
	public float radius = 45;	//37 character in total
	private float iniX, iniY;
	public float x, y;
	private String R ,G ,B;
	private HashMap<Character,Integer> interact = new HashMap<Character,Integer>();
	private ArrayList<Character> target = new ArrayList<Character>();

	public Character(MainApplet parent, String name, String color, int seq){

		this.parent = parent;
		this.name = name;
		this.R = color.substring(3,5);
		this.G = color.substring(5,7);
		this.B = color.substring(7,9);
		iniX = 30+(seq/10)*(radius+15);
		iniY = 45+(seq%10)*(radius+15);
		x = iniX;
		y = iniY;
	}

	public void display(){
		parent.fill(Integer.parseInt(R,16),Integer.parseInt(G,16),Integer.parseInt(B,16));
		parent.ellipse(this.x, this.y, radius, radius);
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
	
	public String getName(){
		return name;
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setInitial(){
		this.x = iniX;
		this.y = iniY;
	}
	
	/*!! new functions*/
	public float getiniX()
	{
		return iniX;
	}
	
	public float getiniY()
	{
		return iniY;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
}
