package Pokemon;

import java.util.LinkedList;
import java.util.List;

public class Trainer {

	private String name;
	private List<Pokemon> poke = new LinkedList<>();
	
	public Trainer(String name) {
		this.name = name;
	}
	
	public void addPokemon(Pokemon pokemon) {
		this.poke.add(pokemon);
	}

	public List<Pokemon> getPokemon(){
		return this.poke;
	}
	
	public void setMega(int num, String version) {
		if(this.poke.get(num).getMega().isEmpty()) return;
		if(version.equals("")) {
			this.poke.add(num, this.poke.remove(num).getMega().get(0));
			return;
		}
		if(version.equals("X")) {
			this.poke.add(num, this.poke.remove(num).getMega().get(0));
			return;
		}
		if(version.equals("Y")) {
			this.poke.add(num, this.poke.remove(num).getMega().get(1));
			return;
		}
	}
	
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return this.name;
	}
}
