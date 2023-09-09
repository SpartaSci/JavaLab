package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import Pokemon.Pokedex;
import Pokemon.Pokemon;

public class TestR3 {

	Pokedex px;
	@Before
	public void setUp() {
		px = Pokedex.fromFile("pokedex.csv", "type.csv");
	}
	@Test
	public void testAddTrainer() {
		px.addTrainer("Ash");
		px.addTrainer("Ash");
	}
	@Test
	public void testAddPokemon() {
		px.addTrainer("Ash");
		px.addPokemon("Ash", 67);
		px.addPokemon("Ash",1,3,6);
		List<Pokemon> lista = px.getPokemon("Ash");
		assertEquals(4,lista.size());
		px.addTrainer("Ash");
		lista = px.getPokemon("Ash");
		assertEquals("new Ash delete old Ash",0,lista.size());
	}
	
	@Test
	public void testAddPokemonMAX6() {
		px.addTrainer("Ash");
		px.addPokemon("Ash", 2, 3, 5, 1, 4, 6, 151);
		List<Pokemon> lista = px.getPokemon("Ash");
		if(lista.stream().anyMatch(p -> p.getNum()==151)) {
			fail();
		}
	}
	
	@Test
	public void testSetMega() {
		px.addTrainer("Ash");
		px.addPokemon("Ash", 1, 22, 3, 77, 59, 6);
		px.setMega("Ash", 5, "Y");
		List<String> lista = px.getPokemon("Ash").stream().map(p -> p.getName()).collect(Collectors.toList());
		if(!lista.contains("Mega Charizard Y")) fail();
		px.setMega("Ash", 2, "");
		lista = px.getPokemon("Ash").stream().map(p -> p.getName()).collect(Collectors.toList());
		if(lista.contains("Mega Venusaur")) fail();
		
	}
}
