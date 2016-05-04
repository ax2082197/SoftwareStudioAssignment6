package main.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	
	private ArrayList<Character> outputList = new ArrayList<Character>();
	private String path = "main/resources/";
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
	Map characters = new < Integer, ArrayList<Character>> HashMap(); //< episode ,character list>
	
	
	public void setup() {

		size(width, height);
		smooth();
		loadData();
		
	}

	public void draw() {
		for(int i=0; i<7; i++){
			chList
		}
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
			characters.put(	j+1, chList);
		
			for(int i = 0; i<links.size(); i++){
				JSONObject link = links.getJSONObject(i);
				Character s =(Character) chList.get(link.getInt("source"));
				Character t =(Character) chList.get(link.getInt("target"));
				s.addTarget(t, link.getInt("value"));
			}
		}
	}

}
