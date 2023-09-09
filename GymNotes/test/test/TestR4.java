package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import GymNotes.GymNotes;
import GymNotes.GymNotesException;

public class TestR4 {

	GymNotes g;

	@Before
	public void loadData() throws GymNotesException{
		g = new GymNotes("exercise.csv");
		g.newClient("Simone", "Poli", "sim@gmail.com");
		g.newClient("Andrea", "Bob", "and@gmail.com");
		g.newClient("Franco", "Mollica", "fra@gmail.com");
		g.newClient("Giorno", "Pescheria", "gio@gmail.com");
		
		g.personalData("sim@gmail.com", 80, 178, "M");
		g.dataSharing("sim@gmail.com");
		g.personalData("and@gmail.com", 90, 180, "M");
		g.dataSharing("and@gmail.com");

		g.addPersonalExercise("sim@gmail.com", "Plank", null, true, "min");
		g.addPersonalExercise("sim@gmail.com", "Alzate laterali", "manubri", false, "rep");
		g.addPersonalExercise("sim@gmail.com", "Squat", "bilanciere", false, "rep");
		
		g.addRoutine("sim@gmail.com", "Push");
		g.addRoutine("sim@gmail.com", "Legs");
		g.addExerciseToRoutine("sim@gmail.com", "Push", "Panca piana bilanciere", "5x5");
		g.addExerciseToRoutine("sim@gmail.com", "Push", "Panca piana manubri", "3x8");
		g.addExerciseToRoutine("sim@gmail.com", "Push", "Plank", "2x60");
		g.addExerciseToRoutine("sim@gmail.com", "Legs", "Squat", "4x10");
		g.addExerciseToRoutine("sim@gmail.com", "Legs", "Corsa", "1x500");
		
		g.addRoutine("and@gmail.com", "Upper");
		g.addExerciseToRoutine("and@gmail.com", "Upper", "Panca piana bilanciere", "4x6");
		g.addExerciseToRoutine("and@gmail.com", "Upper", "Corsa", "1x400");
		
		g.addRoutine("gio@gmail.com", "Push");
		g.addExerciseToRoutine("gio@gmail.com", "Push", "Panca piana bilanciere", "5x5");
		
		g.addWorkout("sim@gmail.com", "Push");
		g.endWorkout("sim@gmail.com", "50 5 5 5 5 4", "20 8 8 6", "45 50");
		g.addWorkout("sim@gmail.com", "Legs");
		g.endWorkout("sim@gmail.com", "80 10 10 10 90", "450");
		g.addWorkout("sim@gmail.com", "Legs");
		g.endWorkout("sim@gmail.com", "80 10 10 10 90", "650");
		g.addWorkout("sim@gmail.com", "Legs");
		g.endWorkout("sim@gmail.com", "80 10 10 10 90", "750");
		g.addWorkout("sim@gmail.com", "Push");
		g.endWorkout("sim@gmail.com", "50 5 5 5 5 6", "20 8 8 8", "60 62");
		g.addWorkout("and@gmail.com", "Upper");
		g.endWorkout("and@gmail.com", "50 5 5 5 5", "420");
		g.addWorkout("and@gmail.com", "Upper");
		g.endWorkout("and@gmail.com", "50 5 5 5 5", "430");
		g.addWorkout("and@gmail.com", "Upper");
		g.endWorkout("and@gmail.com", "50 5 5 5 5", "400");
		g.addWorkout("gio@gmail.com", "Push");
		g.endWorkout("gio@gmail.com", "80 5 5 5 5 5");
		
	}
	
	
	@Test
	public void testGetProgression() throws GymNotesException{
		List<String> lista = g.getProgression("sim@gmail.com","Panca piana bilanciere");
		assertEquals("size",2, lista.size());
		assertEquals("1", "50 5 5 5 5 4", lista.get(0));
	}
	@Test (expected = GymNotesException.class)
	public void testGetProgressionNoEmail() throws GymNotesException{
		g.getProgression("luc@gmail.com","Panca piana bilanciere");
	}
	@Test (expected = GymNotesException.class)
	public void testGetProgressionNoPermission() throws GymNotesException{
		g.getProgression("fra@gmail.com","Panca piana bilanciere");		
	}
	
	@Test (expected = GymNotesException.class)
	public void testGetProgressionNoExe() throws GymNotesException{
		g.getProgression("sim@gmail.com","Lancio");
	}
	
	
	@Test
	public void testAvgMaxTon() throws GymNotesException{
		Double avg = g.avgMaxTon("Panca piana bilanciere");
		assertEquals("AVG", (Object) 1150.0, avg); // check dataSharing
		
	}
	@Test (expected = GymNotesException.class)
	public void testAvgNoExercise() throws GymNotesException{
		g.avgMaxTon("Lancio");
	}
	@Test (expected = GymNotesException.class)
	public void testAvgNoPersonalExercise() throws GymNotesException{
		g.avgMaxTon("Squat");
	}
	@Test (expected = GymNotesException.class)
	public void testAvgNoMinEx() throws GymNotesException{
		g.avgMaxTon("Corsa");
	}
	
	@Test
	public void testMaxSec() throws GymNotesException{
		List<String> lista = g.maxSec("Corsa");
		assertEquals("size", 2, lista.size());
		assertEquals("first","Simone 750", lista.get(0));
	}
}




