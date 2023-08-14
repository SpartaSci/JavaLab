package Autostop;

import java.util.ArrayList;
import java.util.LinkedList;

public class City {
	private String name;
	private LinkedList<String> nearCities = new LinkedList<String>();
	
	public City(String name) {
		this.name = name;
	}
	
	public void addNearCity(String name) {
		this.nearCities.add(name);
	}
	public LinkedList<String> getNearCities(){
		return nearCities;
	}
}
