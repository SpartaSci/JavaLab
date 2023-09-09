package Olympics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public abstract class Sport{
	
	private String name;
	private Map<String,String> results = new HashMap<String, String>(); // <Ita1, "90">
	
	public Sport(String name) throws Exception{
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	public Map<String,String> getResults(){
		return this.results;
	}
	
	
	public abstract boolean checkResult(String result); // vedere se la stringa ottenuta è compatibile con il formato dei risultati 
	
	public void addResult(String athlete, String result) {
		this.results.put(athlete, result);
	}
	public abstract Comparator<String> comparatorResults(); // nella corsa è primo chi ha il tempo minore, nel salto in alto il contrario
	// in questo modo possiamo aggiungere nuovi sport dovendo implementare solo il modo di comparare i risulati
	
	public abstract String toString();
}
