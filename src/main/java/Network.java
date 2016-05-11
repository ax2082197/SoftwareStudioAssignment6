package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import processing.core.PApplet;
import java.util.Random;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private int iniXCenter;
	private int iniYCenter;
	private int radius;
	private ArrayList<Character> node = new ArrayList<Character>();
	private ArrayList<Character> targetList = new ArrayList<Character>();
	private HashMap<Character,Integer> interact = new HashMap<Character,Integer>();
	List <Integer> ls = new ArrayList<>();
	private int num_select;
	private int number ;
	
	public Network(PApplet parent){

		this.parent = parent;
		iniXCenter = 600;
		iniYCenter = 300;
		radius = 500;
		num_select =0;
		number = 0;
		ls.add(0);ls.add(2);ls.add(5);ls.add(10);ls.add(20);ls.add(30);ls.add(40);
		ls.add(50);ls.add(60);ls.add(80);ls.add(100);
		ls.add(120);ls.add(140);ls.add(160);ls.add(180);ls.add(200);
		ls.add(220);ls.add(240);ls.add(260);ls.add(280);ls.add(300);
		ls.add(320);ls.add(340);ls.add(360);ls.add(380);ls.add(400);
		ls.add(420);ls.add(440);ls.add(450);
		ls.add(460);ls.add(470);ls.add(480);ls.add(490);ls.add(493);ls.add(496);ls.add(498);ls.add(500);
	}
	
	/*display the circle*/
	public void display(){
		parent.noFill();//don't fill any color in the ellipse
		parent.ellipse(iniXCenter, iniYCenter, radius, radius);
		
		for( Character ch:node){
			targetList = ch.getTarget();
			for( Character ch1 : targetList){
				if(node.contains(ch1)){
					parent.line(ch.getX(),ch.getY(),ch1.getX(),ch1.getY());
					interact = ch.getInteract();
					if(interact.get(ch1)<5)
						parent.strokeWeight(1);
					else if(interact.get(ch1)<10)
						parent.strokeWeight(3);
					else
						parent.strokeWeight(5);

				}
			}
		}
		
	}
	
	public void changePosition()
	{
		Random num = new Random();
		for(Character ch:node){
			double xp;
			xp = ls.get(num_select)+this.getX()-this.getRadius();
			num_select+=1;
			num_select = num_select%37;
			double ytmp = 62500-(xp-this.iniXCenter)*(xp-this.iniXCenter);
			double yp = Math.sqrt(ytmp);
			
			if( number ==0 )
				number = 1;
			else{
				yp*=-1;
				number = 0;
			}
			yp+=300;
			ch.setPosition((float)xp,(float)yp);
		}
	}
	
	public int getX()
	{
		return iniXCenter;
	}
	
	public int getY()
	{
		return iniYCenter;
	}
	
	public int getRadius()
	{
		return radius/2;
	}
	
	public void addNode(Character ch)
	{
		node.add(ch);
	}
	
	public boolean isNodeExist(Character ch)
	{
		if(node.contains(ch))
			return true;
		else
			return false;
	}
	
	public void deletenode(Character ch)
	{
		node.remove(ch);
	}
	
	public void addAllNode(ArrayList<Character> all)
	{
		for( Character ch:all){
			if(node.contains(ch)==false)
				this.addNode(ch);
		}
		this.changePosition();//change all node's position
	}
	
	public void deleteAllNode(ArrayList<Character> all)
	{
		for( Character ch:all){
			if(node.contains(ch)){
				this.deletenode(ch);
				ch.setPosition(ch.getiniX(), ch.getiniY());	
			}
		}
	}
}
