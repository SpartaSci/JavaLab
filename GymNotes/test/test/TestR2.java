package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import GymNotes.Exercise;
import GymNotes.GymNotes;
import GymNotes.GymNotesException;



public class TestR2 {

	GymNotes g;

	@Before
	public void loadData() throws GymNotesException{
		g = new GymNotes("exercise.csv");
		g.newClient("Simone", "Poli", "sim@gmail.com");
		g.newClient("Andrea", "Bob", "and@gmail.com");
		g.newClient("Franco", "Mollica", "fra@gmail.com");
		g.newClient("Giorno", "Pescheria", "gio@gmail.com");
	}
	
	@Test (expected = GymNotesException.class)
	public void testAddPersonalExNoEmail() throws GymNotesException{
		g.addPersonalExercise("luca@gmail.com", "Plank", null, true, "min");
	}
	@Test (expected = GymNotesException.class)
	public void testAddPersonalExDuplicatedEx() throws GymNotesException{
		g.addPersonalExercise("sim@gmail.com", "Panca piana manubri", null, true, "min");
	}
	
	@Test (expected = GymNotesException.class)
	public void testAddPersonalExMinOrRep() throws GymNotesException{
		g.addPersonalExercise("sim@gmail.com", "Plank", null, true, "bho");
	}
	@Test (expected = GymNotesException.class)
	public void testGetAllExerciseNoEmail() throws GymNotesException{
		g.getAllExercise("Gio");
	}
	@Test
	public void testGetAllExercise() throws GymNotesException{
		g.addPersonalExercise("sim@gmail.com", "Plank", null, true, "min");
		List<String> lista = g.getAllExercise("sim@gmail.com").stream().map(p -> p.getName()).collect(Collectors.toList());
		assertEquals("size",10, lista.size());
		assertTrue("personal ex", lista.contains("Plank"));
	}
	
	@Test (expected = GymNotesException.class)
	public void testGetBWException() throws GymNotesException{
		g.getBWexercise("Luc@gmail.com");
	}
	@Test 
	public void testGetBw() throws GymNotesException{
		g.addPersonalExercise("sim@gmail.com", "Plank", null, true, "min");
		List<Exercise> lista = g.getBWexercise();
		assertEquals("Only default",2, lista.size());
		lista = g.getBWexercise("sim@gmail.com");
		assertEquals("Also personal",3, lista.size());
	}
	@Test (expected = GymNotesException.class)
	public void testGetExByToolException() throws GymNotesException{
		g.getExerciseByTools("manubri", "luc@gmail.com");
	}
	@Test 
	public void testGetExByTool() throws GymNotesException{
		g.addPersonalExercise("sim@gmail.com", "Alzate laterali", "manubri", false, "rep");
		List<Exercise> lista = g.getExerciseByTools("manubri");
		assertEquals("Only default",2, lista.size());
		lista = g.getExerciseByTools("manubri", "sim@gmail.com");
		assertEquals("Also personal",3, lista.size());
	}
}
