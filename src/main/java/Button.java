package main.java;
import java.util.ArrayList;

import controlP5.*;

public class Button {
	private ControlP5 cp5;
	private String msg = "";
	private MainApplet parent;
	private ArrayList<Character> node = new ArrayList<Character>();
	public Button(MainApplet parent){
		this.parent = parent;
		parent.size(3600, 370);
		cp5 = new ControlP5(parent);
		cp5.addButton("buttonA")
		.setLabel("ADD ALL")
		.setPosition(3600/4, 370/2)
		.setSize(200, 50);
		cp5.addButton("buttonB")
		.setLabel("Clear")
		.setPosition(3600/4, 3*370/4)
		.setSize(200, 50);
	}
	public void display(){
		//parent.fill(0);
		parent.textSize(26);
		parent.text(msg, 3600/4, 370/4);
		System.out.println(msg);
	}
	
}