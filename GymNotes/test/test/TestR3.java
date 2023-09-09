package test;

import org.junit.Before;
import org.junit.Test;

import GymNotes.GymNotes;
import GymNotes.GymNotesException;

public class TestR3 {

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
		g.addPersonalExercise("sim@gmail.com", "Plank", null, true, "min");
		g.addPersonalExercise("sim@gmail.com", "Alzate laterali", "manubri", false, "rep");
		
		g.addRoutine("sim@gmail.com", "Push");
	}

	@Test (expected = GymNotesException.class)
	public void testAddRoutineNoEmail() throws GymNotesException{
		g.addRoutine("luc@gmail.com", "Push");
	}
	@Test (expected = GymNotesException.class)
	public void testAddRoutineDouble() throws GymNotesException{
		g.addRoutine("sim@gmail.com", "Push");
		g.addRoutine("sim@gmail.com", "Push");
	}
	@Test (expected = GymNotesException.class)
	public void testAddRoutineMaxRoutieìne() throws GymNotesException{
		g.addRoutine("sim@gmail.com", "Push2");
		g.addRoutine("sim@gmail.com", "Pull");
		g.addRoutine("sim@gmail.com", "Legs");
		g.addRoutine("sim@gmail.com", "Upper");
		g.addRoutine("sim@gmail.com", "Lower");
		g.addRoutine("sim@gmail.com", "Full");
		g.addRoutine("sim@gmail.com", "Cardio");
		g.addRoutine("sim@gmail.com", "Functional");
	}
	@Test (expected = GymNotesException.class)
	public void testAddExerciseToRoutineNoEmail() throws GymNotesException{
		g.addExerciseToRoutine("luc@gmail.com", "Push", null, null);
	}
	@Test (expected = GymNotesException.class)
	public void testAddExerciseToRoutineNoRoutine() throws GymNotesException{
		g.addExerciseToRoutine("sim@gmail.com", "Pull", null, null);
	}
	
	@Test (expected = GymNotesException.class)
	public void testAddExerciseToRoutineNoExercise() throws GymNotesException {
		g.addExerciseToRoutine("sim@gmail.com", "Push", "Lancio", "5x5");
	}
	@Test (expected = GymNotesException.class)
	public void testAddExerciseToRoutineSet() throws GymNotesException {
		g.addExerciseToRoutine("sim@gmail.com", "Push", "Panca piana bilanciere", "x5");
	}
	
	@Test (expected = GymNotesException.class)
	public void testGetRoutineNoEmail() throws GymNotesException {
		g.getRoutine("luc@gmail.com", "Push");
	}
	@Test (expected = GymNotesException.class)
	public void testGetRoutineNoRoutine() throws GymNotesException {
		g.getRoutine("sim@gmail.com", "Pull");
	}
	
	
	
}

