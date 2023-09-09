package TestApp;

import Pokemon.Pokedex;

public class TestApp {

	public static void main(String[] args) {
		
		Pokedex px = Pokedex.fromFile("pokedex.csv","type.csv");
		
		px.getType();
		px.getProperties("Flying").entrySet().forEach(l -> System.out.println(l));
		
		px.getListPokemon().forEach(c -> System.out.println(c));
		px.getListPokemon("Fire");
		px.getListPokemonByStat("Atk");
		px.getListPokemonByStat("Total");
		px.getListPokemonByStat("Hp");
		
		px.addTrainer("Ash");
		px.addPokemon("Ash", 6);
		px.addPokemon("Ash", 3);
		px.addPokemon("Ash", 56, 144, 96, 111, 63, 4 );
		px.addTrainer("Brock");
		px.addPokemon("Brock", 0,0,0,0,0,0,0,0);
		px.addTrainer("Misty");
		px.addPokemon("Misty", 1,1,1,1,1,1,1,1);
		px.addTrainer("Blu");
		px.addPokemon("Blu", 150,150,150,150,150,150,150);
		
		System.out.println(px.getPokemon("Ash"));
		
		px.setMega("Ash", 0, "Y");
		px.setMega("Ash", 1, "");
		
		System.out.println(px.getPokemon("Ash"));
		
		System.out.println(px.getMega());
		System.out.println(px.trainerByStat());
		px.getMapType().values().stream().flatMap(p -> p.stream()).forEach(System.out::println);
		
	}
}
