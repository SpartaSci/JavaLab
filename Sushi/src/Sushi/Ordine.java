package Sushi;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class Ordine {
	
	private Tavolo table;
	private HashMap<Integer,Integer> plates = new HashMap<>();
	
	protected Ordine(Tavolo t) {
		this.table = t;
	}
	
	protected void addSushi(int s) {
		int tot = plates.getOrDefault(s, 0);
		plates.put(s, tot+1);
	}
	protected List<String> getOrder(){
		return this.plates.entrySet().stream().map(p -> p.getValue()+"x"+p.getKey()).collect(Collectors.toList());
	}
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer();
		b.append("Tavolo ").append(this.table.getNumber()).append(":");
		this.getOrder().forEach(c-> b.append(c).append(";"));
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
}
