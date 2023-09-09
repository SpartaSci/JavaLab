package Example;
import java.io.Serial;
import java.util.LinkedList;
import java.util.List;

import GymNotes.Exercise;
import GymNotes.GymNotes;
import GymNotes.GymNotesException;
public class TestExample {

	public static void main(String[] args) throws GymNotesException {
		
		
		GymNotes g = new GymNotes("exercise.csv");
		//R1
		g.newClient("Simone", "Poli", "sim@gmail.com");
		g.newClient("Andrea", "Bob", "and@gmail.com");
		g.newClient("Franco", "Mollica", "fra@gmail.com");
		g.newClient("Giorno", "Pescheria", "gio@gmail.com");
		
		String simone = "sim@gmail.com";
		
		g.personalData(simone, 80, 178, "M");
		g.dataSharing(simone);
		
		try {
			g.dataSharing("fra@gmail.com");
		} catch (Exception e) {
			System.out.println("Deve prima fornire i dati personali");
		}
		
		//R2
		g.addPersonalExercise(simone, "Plank", null, true, "min");
		g.addPersonalExercise(simone, "Alzate laterali", "manubri", false, "rep");
		System.out.println(g.getAllExercise(simone));
		System.out.println(g.getBWexercise());
		System.out.println(g.getExerciseByTools("manubri"));
		System.out.println(g.getExerciseByTools("manubri",simone));
		
		List<Exercise> allEx = g.getAllExercise(simone);
		List<Exercise> e = new LinkedList<Exercise>();
		//R3
		g.addRoutine(simone,"Push");
		g.addExerciseToRoutine(simone, "Push", "Panca piana bilanciere", "5x5");
		g.addExerciseToRoutine(simone, "Push", "Panca piana manubri", "3x8");
		g.addExerciseToRoutine(simone, "Push", "Plank", "2x60");
		System.out.println(g.getRoutine(simone, "Push"));
		try {
			g.getRoutine(simone, "pull");
		}catch (Exception ecc) {
			// TODO: handle exception
			System.out.println("Non è presente la routine");
		}
		
		g.addWorkout(simone, "Push");
		g.endWorkout(simone, "50 5 5 5 5 4", "20 8 8 6", "60 60");
		g.addWorkout(simone, "Push");
		g.endWorkout(simone, "50 5 5 5 5 5", "20 8 8 7", "60 60");
		g.addExerciseToRoutine(simone, "Push", "Corsa", "2x400");
		g.addWorkout(simone, "Push");
		g.endWorkout(simone, "60 4 4 4 4 4", "20 8 8 8", "60 60", "450 400");
		System.out.println(g.getProgression(simone, "Panca piana bilanciere"));
		
		g.addRoutine("and@gmail.com", "Upper");
		g.addExerciseToRoutine("and@gmail.com", "Upper", "Panca piana bilanciere", "4x6");
		g.addExerciseToRoutine("and@gmail.com", "Upper", "Corsa", "1x400");
		g.addWorkout("and@gmail.com", "Upper");
		g.endWorkout("and@gmail.com", "80 6 6 6 5", "420");
		g.personalData("and@gmail.com", 90, 180, "M");
		g.dataSharing("and@gmail.com");

		System.out.println(g.avgMaxTon("Panca piana bilanciere"));
		
		System.out.println(g.maxSec("Corsa"));
		
	}

}
