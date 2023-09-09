package test;

import org.junit.Before;
import org.junit.Test;

import GymNotes.GymNotes;
import GymNotes.GymNotesException;

public class TestR3_2 {

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
		g.addRoutine("sim@gmail.com", "Legs");
		g.addExerciseToRoutine("sim@gmail.com", "Push", "Panca piana bilanciere", "5x5");
		g.addExerciseToRoutine("sim@gmail.com", "Push", "Panca piana manubri", "3x8");
		g.addExerciseToRoutine("sim@gmail.com", "Push", "Plank", "2x60");
		
		g.addRoutine("and@gmail.com", "Upper");
		g.addExerciseToRoutine("and@gmail.com", "Upper", "Panca piana bilanciere", "4x6");
		g.addExerciseToRoutine("and@gmail.com", "Upper", "Corsa", "1x400");
	}
	
	@Test (expected = GymNotesException.class)
	public void testaddWorkoutNoEmail() throws GymNotesException{
		g.addWorkout("luc", "Push");
	}
	@Test (expected = GymNotesException.class)
	public void testaddWorkoutNoRoutine() throws GymNotesException{
		g.addWorkout("sim@gmail.com", "Pull");
	}
	@Test (expected = GymNotesException.class)
	public void testaddWorkoutOpenSessione() throws GymNotesException{
		g.addWorkout("sim@gmail.com", "Push");
		g.addWorkout("sim@gmail.com", "Legs");
	}
	
	
	@Test 
	public void testEndWorkout() throws GymNotesException{
		g.addWorkout("sim@gmail.com", "Push");
		g.endWorkout("sim@gmail.com", "50 5 5 5 5 4", "20 8 8 6", "60 60");
	}
	
	@Test (expected = GymNotesException.class)
	public void testEndWorkoutNoEmail() throws GymNotesException{
		g.addWorkout("sim@gmail.com", "Push");
		g.endWorkout("luc@gmail.com", "50 5 5 5 5 4", "20 8 8 6", "60 60");
		
	}
	
	@Test (expected = GymNotesException.class)
	public void testEndWorkoutNoWorkout() throws GymNotesException{
		g.endWorkout("sim@gmail.com", "50 5 5 5 5 4", "20 8 8 6", "60 60");
	}
	@Test (expected = GymNotesException.class)
	public void testEndWorkoutNotOpen() throws GymNotesException{
		g.addWorkout("sim@gmail.com", "Push");
		g.endWorkout("sim@gmail.com", "50 5 5 5 5 4", "20 8 8 6", "60 60");
		g.endWorkout("sim@gmail.com", "50 5 5 5 5 4", "20 8 8 6", "60 60");
	}
	
	@Test (expected = GymNotesException.class)
	public void testEndWorkoutNumExercise() throws GymNotesException{
		g.addWorkout("sim@gmail.com", "Push");
		g.endWorkout("sim@gmail.com", "50 5 5 5 5 4", "20 8 8 6");
	}
	@Test (expected = GymNotesException.class)
	public void testEndWorkoutSet() throws GymNotesException{
		g.addWorkout("sim@gmail.com", "Push");
		g.endWorkout("sim@gmail.com", "50 5 5 5 5 4", "20 8 6", "60 60");
	}
	
	
	
}
