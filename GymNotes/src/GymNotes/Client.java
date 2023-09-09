package GymNotes;

import java.util.LinkedList;
import java.util.List;

public class Client {

	private String name;
	private String surname;
	private String email;
	private int weight;
	private int height;
	private String gender;
	
	private List<Exercise> exercises = new LinkedList<Exercise>();
	private List<Routine> routines = new LinkedList<Routine>();
	private List<Workout> workouts = new LinkedList<Workout>();
	
	private boolean consent = false;
	private boolean personalData = false;
	
	public Client(String name, String surname, String email) {
		this.name = name;
		this.surname = surname;
		this.email = email;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}
	
	public void personalData() {
		this.personalData = true;
	}
	public boolean getPersonalData() {
		return personalData;
	}
	public boolean isConsenting() {
		return consent;
	}
	public void acceptDataSharing() {
		this.consent = true;
	}
	
	
	public void addExercise(Exercise e) {
		this.exercises.add(e);
	}
	public List<Exercise> getExercises(){
		return this.exercises;
	}
	public boolean containExercise(String name) {
		return this.exercises.stream().map(Exercise::getName).anyMatch(c -> c.equals(name));
		
	}
	public Exercise getExercise(String name) {
		return this.exercises.stream().filter(c -> c.getName()==name).findAny().orElseThrow();
	}
	
	public void addRoutine(Routine r) {
		this.routines.add(r);
	}
	public List<Routine> getRoutines() {
		return this.routines;
	}
	public boolean containRoutine(String name) {
		return this.routines.stream().map(Routine::getName).anyMatch(c -> c.equals(name));
	}
	public Routine getRoutine(String name) {
		return this.routines.stream().filter(c -> c.getName()==name).findAny().get();
	}
	
	public void addWorkout(Workout workout) {
		this.workouts.add(workout);
	}
	public Workout getLastWorkout() {
		return this.workouts.get(workouts.size()-1);
	}
	public List<Workout> getWorkouts(){
		return this.workouts;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.email;
	}
}
