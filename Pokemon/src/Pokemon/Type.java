package Pokemon;

import java.util.HashMap;
import java.util.Map;

public class Type {
	private String name;
	private HashMap<String, Double> eff = new HashMap<>();
	
	public Type(String name) {
		this.name = name;
	}
	public void addEff(String type, Double eff) {
		this.eff.put(type, eff);
	}
	public Map<String,Double> getProp(){
		return this.eff;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
}
