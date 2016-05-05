package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	
	Map characters = new < Integer, ArrayList<Character>> HashMap(); //< episode ,character list>
	private ArrayList<Character> outputList;
	private String path = "main/resources/";
	private int ep;
	private boolean dragging;
	private Character chosen;
	private String[] file = new String[]{
			"starwars-episode-1-interactions.json",
			"starwars-episode-2-interactions.json",
			"starwars-episode-3-interactions.json",
			"starwars-episode-4-interactions.json",
			"starwars-episode-5-interactions.json",
			"starwars-episode-6-interactions.json",
			"starwars-episode-7-interactions.json"
	};
	
	private final static int width = 1200, height = 650;
	
	public void setup() {

		size(width, height);
		ep = 0;
		smooth();
		loadData();
		
	}

	public void draw() {
		background(255);
		for(Character it : outputList){
			it.display();
		}
		for(Character it : outputList){
			if(Math.abs(mouseX-it.x)<it.radius/2&&Math.abs(mouseY-it.y)<it.radius/2){
				if(dragging == false || (dragging == true&& it==chosen)){
					fill(255,255,0);
					rect(it.x,it.y,60+(it.getName().length()-4)*7,30);
					fill(0,0,0);
					text( it.getName(), it.x+12, it.y+20);
				}
				if(mousePressed){
					if(dragging==false) chosen = it;
					dragging = true;
					
				}else{
					dragging = false;
					chosen = null;
				}
			}
		}
		if(dragging == true && chosen != null)
			chosen.setPosition(mouseX, mouseY);
	}

	public void keyPressed(){
		if(keyCode == KeyEvent.VK_1) setOutputList(0);
		if(keyCode == KeyEvent.VK_2) setOutputList(1);
		if(keyCode == KeyEvent.VK_3) setOutputList(2);
		if(keyCode == KeyEvent.VK_4) setOutputList(3);
		if(keyCode == KeyEvent.VK_5) setOutputList(4);
		if(keyCode == KeyEvent.VK_6) setOutputList(5);
		if(keyCode == KeyEvent.VK_7) setOutputList(6);
	}

	private void loadData(){
		for(int j=0; j<7; j++){
			JSONObject data = loadJSONObject(path.concat(file[j]));
			JSONArray nodes = data.getJSONArray("nodes");
			JSONArray links = data.getJSONArray("links");
			List chList = new ArrayList();
			for(int i = 0; i<nodes.size(); i++){
				JSONObject node = nodes.getJSONObject(i);
				Character ch = new Character( this, node.getString("name"), node.getString("colour"), i);
				chList.add(ch);
			}
			characters.put(	j, chList);
		
			for(int i = 0; i<links.size(); i++){
				JSONObject link = links.getJSONObject(i);
				Character s =(Character) chList.get(link.getInt("source"));
				Character t =(Character) chList.get(link.getInt("target"));
				s.addTarget(t, link.getInt("value"));
			}
		}
		setOutputList(0);
	}
	
	private void setOutputList(int ep){
		outputList = (ArrayList<Character>) characters.get(ep);
	}
}
