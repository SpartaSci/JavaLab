package Pokemon;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Pokedex {
	
	private Map<Integer,Pokemon> pokemon = new TreeMap<Integer,Pokemon>();
	private List<Type> types = new LinkedList<Type>();
	private Map<String,Trainer> trainers = new HashMap<String, Trainer>();
	
	
	public static Pokedex fromFile(String file1, String file2) {
		Pokedex px = new Pokedex();
		px.loadType(px, file2);
		px.loadPokemon(px, file1);
		return px;
	}
	private static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	//R1
	private void loadType(Pokedex p, String file){
		List<String> lines = Pokedex.readData(file);
		String[] headers = lines.remove(0).split(",");
		lines.forEach(row -> {
			String[] cells = row.split(",");
			Type t = new Type(cells[0]);
			
			for(int i = 1; i<cells.length; i++) {
				t.addEff(headers[i], Double.parseDouble(cells[i]));
			}
			p.types.add(t);
		});	
	}
	public List<Type> getType(){
		return this.types;
	}
			//off,def    2,0.5,0     Type
	public Map<String, Map<String, List<String>>> getProperties(String type){	
		Type ty = this.types.stream().filter(c -> c.getName().equals(type)).findFirst().orElse(null);
		Map<String, Map<String, List<String>>> result = new TreeMap<String, Map<String,List<String>>>();
		if(ty == null) return result;
		
		Map<String, List<String>> resParziale1 = new TreeMap<String, List<String>>();
		resParziale1.put("x2", new LinkedList<String>());
		resParziale1.put("x0.5", new LinkedList<String>());
		resParziale1.put("x0", new LinkedList<String>());
		Map<String, List<String>> resParziale2 = new TreeMap<String, List<String>>();
		resParziale2.put("x2", new LinkedList<String>());
		resParziale2.put("x0.5", new LinkedList<String>());
		resParziale2.put("x0", new LinkedList<String>());
		
		
		result.put("Offense", new HashMap<String, List<String>>(resParziale1));
		result.put("Defense", new HashMap<String, List<String>>(resParziale2));
		
		//defense
		this.types.forEach(t -> {
			Double valore= t.getProp().get(type);
			if(valore==2) {
				result.get("Defense").get("x2").add(t.getName());
			} else if (valore == 0.5) {
				result.get("Defense").get("x0.5").add(t.getName());
			} else if (valore == 0) {
				result.get("Defense").get("x0").add(t.getName());
			}
		});
		
		//offense
		
		ty.getProp().entrySet().forEach(t -> {
			if(t.getValue()==2) {
				result.get("Offense").get("x2").add(t.getKey());
			} else if (t.getValue()== 0.5) {
				result.get("Offense").get("x0.5").add(t.getKey());
			} else if (t.getValue()== 0) {
				result.get("Offense").get("x0").add(t.getKey());
			}
		});
		
		return result;
	}
	
	//R2
	private void loadPokemon(Pokedex px, String file){
		List<String> lines = Pokedex.readData(file);
		lines.forEach(row -> {
			String[] cells = row.split(",");

			Pokemon p = new Pokemon(Integer.parseInt(cells[0]),cells[1],cells[2],cells[3],
					Integer.parseInt(cells[4]),Integer.parseInt(cells[5]),
					Integer.parseInt(cells[6]),Integer.parseInt(cells[7]),
					Integer.parseInt(cells[8]),Integer.parseInt(cells[9]),
					Integer.parseInt(cells[10]));
			if(px.pokemon.containsKey(Integer.parseInt(cells[0]))) { //mega
				px.pokemon.get(Integer.parseInt(cells[0])).addMega(p);
			}else {
				px.newPokemon(p);
			}
		});
	}
	private void newPokemon(Pokemon p) {
		this.pokemon.put(p.getNum(), p);
	}
	
	
	public List<String> getListPokemon(){
		return this.pokemon.values().stream().map(c->c.toString()).collect(Collectors.toList());
	}
	
	public List<String> getListPokemon(String type){
		return this.pokemon.values().stream().filter(p -> p.getType1().equals(type) || p.getType2().equals(type))
				.map(p -> p.toString()).collect(Collectors.toList());
	}
	public List<String> getListPokemonByStat(String stat){
		
		return this.pokemon.values().stream()
				.collect(Collectors.toMap(Pokemon::getName, p->p.getStatByName(stat)))
				.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.map(m -> m.getKey()+" "+m.getValue()).collect(Collectors.toList());
				
	}
	
	
	
	
	// R3
	
	public void addTrainer(String name) {
		Trainer t = new Trainer(name);
		this.trainers.put(name, t);
	}
		
	public void addPokemon(String name, int pokemon) {
		if(!trainers.containsKey(name)) return;
		Trainer t = this.trainers.get(name);
		if(t.getPokemon().size()==6) return;
		if(!this.pokemon.containsKey(pokemon)) return;
		t.addPokemon(this.pokemon.get(pokemon));
	}
	public void addPokemon(String name, int ...pokemon) {
		for(int p: pokemon) {
			this.addPokemon(name, p);
		}
	}
	
	public List<Pokemon> getPokemon(String name){
		if(!this.trainers.containsKey(name)) return null;
		return this.trainers.get(name).getPokemon();
	}
	
	public void setMega(String name, int num, String version) {
		if(!this.trainers.containsKey(name)) return;
		Trainer t = this.trainers.get(name);
		if(t.getPokemon().stream().anyMatch(p->p.isMega())) return;
		t.setMega(num, version);
	}
	
	// R4 Filtri Stat e altro
	
	public List<String> getMega(){
		return this.pokemon.values().stream().flatMap(c -> c.getMega().stream()).map(c->c.getName()).collect(Collectors.toList());
	}
	
	public Map<String, List<String>> getMapType(){
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		
		this.types.forEach(t -> {
			List<String> poke = new LinkedList<String>();
			poke.addAll(this.pokemon.values().stream()
								.filter(Pokemon::oneType)
								.filter(p -> p.getType1().equals(t.getName()))
								.map(p -> p.getName() +" "+p.getType1())
								.collect(Collectors.toList()));
			poke.addAll(this.pokemon.values().stream()
								.filter(p -> !p.oneType())
								.filter(p-> p.getType1().equals(t.getName()) || p.getType2().equals(t.getName()))
								.map(p -> p.getName() +" "+p.getType1() + " "+ p.getType2())
								.collect(Collectors.toList()));
			result.put(t.getName(), poke);
		});
		return result;
	}
	
	public List<Trainer> trainerByStat(){
		return this.trainers.values().stream().sorted( Comparator.comparing( t -> {
															return ((Trainer) t).getPokemon().stream().mapToInt(p->p.getTotal()).average().orElse(0);
														})).collect(Collectors.toList());
														 
	}
	
	
	
	
	
}
