package Autostop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import Autostop.Autostop.TravelStatus;

public class Travel {
	private TravelStatus status =  TravelStatus.OPEN;
	
	private String driver;
	private int Max;
	private int free;
	private String time;
	
	private LinkedList<String> stages;
	private HashMap<String,String> users = new HashMap<String,String>();
	
	public Travel(String driver, int N, String time, String[] stages) {
		this.driver = driver;
		this.Max = N;
		this.free = N;
		this.time = time;
		this.stages = new LinkedList<>(List.of(stages));
		
	}
	
	public String getDriver() {
		return this.driver;
	}
	public List<String> getUser(){
		return List.copyOf(this.users.keySet());
	}
	public int getN_available() {
		return free;
	}
	
	public List<String> getStages(){
		return stages;
	}
	public int getNstages() {
		return stages.size();
	}
	
	public int addUser(String user, String stage) {
		if(free == 0) return 0;
		users.put(user,stage);
		free--;
		return free;
	}
	public boolean isOpen() {
		return status.equals(TravelStatus.OPEN);
	}
	public boolean isDone() {
		return status.equals(TravelStatus.DONE);
	}
	public boolean isReady() {
		return status.equals(TravelStatus.READY);
	}
	
	public void ready() {
		this.status = TravelStatus.READY;
	}
	public void done() {
		this.status = TravelStatus.DONE;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.driver + " " + this.time + " "+ this.stages.stream().collect(Collectors.joining(" - "));
	}
	
}
