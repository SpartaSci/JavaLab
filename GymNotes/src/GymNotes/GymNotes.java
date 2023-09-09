package GymNotes;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GymNotes {

	
	private HashMap<String,Client> clients = new HashMap<String, Client>();
	private HashMap<String,Exercise> exercises = new HashMap<String, Exercise>(); //salvo solo i default, quelli personali direttamente su client
	
	public GymNotes(String file) {
		loadData(file);
	}
	
	// R1 
	public void newClient(String name, String surname, String email) throws GymNotesException {
		if(clients.containsKey(email)) throw new GymNotesException();
		Client c = new Client(name, surname, email);
		clients.put(email, c);
	}
	public void personalData(String email, int weight, int height, String gender) throws GymNotesException {
		if(!clients.containsKey(email)) throw new GymNotesException();
		if(!gender.equals("M") && !gender.equals("F")) throw new GymNotesException();
		Client c = this.clients.get(email);
		c.setHeight(height);
		c.setWeight(weight);
		c.setGender(gender);
		c.personalData();
	}
	public void dataSharing(String email) throws GymNotesException{
		if(!clients.containsKey(email)) throw new GymNotesException();
		Client c = this.clients.get(email);
		if(!c.getPersonalData()) throw new GymNotesException();
		c.acceptDataSharing();
	}
	//R2 
	public void loadData(String file) {
		List<String> lines = GymNotes.readData(file);
		lines.forEach(row -> {
			String[] cells = row.split(",");
			String name = cells[0];
			String equipment = cells[1];
			boolean bw = Boolean.parseBoolean(cells[2]);
			String type = cells[3];
			Exercise exercise = new Exercise(name,bw,equipment,type);
			exercises.put(name, exercise);
		});
	}
	
	private static List<String> readData(String file){
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public void addPersonalExercise(String email, String name, String equipment, boolean bw, String type) throws GymNotesException {
		if(!clients.containsKey(email)) throw new GymNotesException();
		if(exercises.containsKey(name)) throw new GymNotesException();
		if(type != "min" && type != "rep") throw new GymNotesException();
		Exercise e = new Exercise(name, bw, equipment, type);
		Client c = this.clients.get(email);
		c.addExercise(e);
		e.personal();
	}
	
	public List<Exercise> getAllExercise(String email) throws GymNotesException{
		if(!clients.containsKey(email)) throw new GymNotesException();
		Client c = this.clients.get(email);
		List<Exercise> ex = new LinkedList<>();
		ex.addAll(this.exercises.values().stream().collect(toList()));
		ex.addAll(c.getExercises());
		return ex;
	}
	
	public List<Exercise> getBWexercise(){
		return this.exercises.values().stream().filter(c-> c.isBodyWeight()).collect(toList()); 
	}
	public List<Exercise> getBWexercise(String email) throws GymNotesException {
		if(!clients.containsKey(email)) throw new GymNotesException();
		List<Exercise> personal = clients.get(email).getExercises().stream().filter(c-> c.isBodyWeight()).collect(toList());
		personal.addAll(getBWexercise());
		return personal;
	}
	public List<Exercise> getExerciseByTools(String eq){
		return this.exercises.values().stream().filter(c->c.getEquipment().equals(eq)).collect(toList());
	}
	public List<Exercise> getExerciseByTools(String eq, String email) throws GymNotesException{
		if(!clients.containsKey(email)) throw new GymNotesException();
		List<Exercise> personal = clients.get(email).getExercises().stream().filter(c-> c.getEquipment()==eq).collect(toList());
		personal.addAll(getExerciseByTools(eq));
		return personal;
	}
	
	//R3 
	
	public void addRoutine(String email, String name) throws GymNotesException{
		if(!clients.containsKey(email)) throw new GymNotesException();
		Client c = this.clients.get(email);
		if(c.getRoutines().size()==6) throw new GymNotesException();
		if(c.containRoutine(name)) throw new GymNotesException();
		Routine r = new Routine(name);
		c.addRoutine(r);
	}
	
	
	public void addExerciseToRoutine(String email, String routine, String exercise, String set) throws GymNotesException {
		if(!clients.containsKey(email)) throw new GymNotesException();
		Client c = this.clients.get(email);
		if(!c.containRoutine(routine)) throw new GymNotesException();
		Routine r = c.getRoutine(routine);
		Exercise e;
		if(this.exercises.containsKey(exercise)) {
			e = this.exercises.get(exercise);
		} else if(c.containExercise(exercise)){
			e = c.getExercise(exercise);
		} else {
			throw new GymNotesException();
		}
		Pattern p = Pattern.compile("[0-9]+x[0-9]+");
		if(!set.matches(p.pattern())) throw new GymNotesException();
		r.addExecise(e, set);
	}
	public String getRoutine(String email, String routine) throws GymNotesException{
		if(!clients.containsKey(email)) throw new GymNotesException();
		if(!this.clients.get(email).containRoutine(routine)) throw new GymNotesException();
		return this.clients.get(email).getRoutine(routine).toString();
	}
	
	public void addWorkout(String email, String name) throws GymNotesException{
		if(!clients.containsKey(email)) throw new GymNotesException();
		Client c = this.clients.get(email);
		if(c.getWorkouts().size()!=0) {
			if(c.getLastWorkout().isOpen()) throw new GymNotesException();
		}
		
		if(!c.containRoutine(name)) throw new GymNotesException();
		Workout workout = new Workout(c.getRoutine(name));
		c.addWorkout(workout);
		
	}
	public void endWorkout(String email, String ...strings ) throws GymNotesException{
		if(!clients.containsKey(email)) throw new GymNotesException("No email");
		Client c = this.clients.get(email);
		if(c.getWorkouts().size()==0) throw new GymNotesException("No workout");
		if(!c.getLastWorkout().isOpen()) throw new GymNotesException("Last wo is not open");
		
		Workout workout = c.getLastWorkout();
		if(workout.exercises.size()!= strings.length) throw new GymNotesException("wrong number of exercise");
		int i = 0;
		for(String s: strings) {
			int kg = 0;
			if(workout.exercises.get(i).needKg()) {
				kg = Integer.parseInt(s.split(" ")[0]);
				s = s.replaceFirst(kg+" ","");
			}
			
			int set =  workout.rep.get(i).charAt(0) - '0';
			if(set != s.split(" ").length) throw new GymNotesException("Numero set sbagliato");
			workout.addExecise(kg,s);
			i++;
		}
		workout.endW();
	}
	
	// R4
	public List<String> getProgression(String email, String nameEx) throws GymNotesException{
		if(!clients.containsKey(email)) throw new GymNotesException();
		Client c = this.clients.get(email);
		if(!c.isConsenting()) throw new GymNotesException();
		if(this.getAllExercise(email).stream().noneMatch(ex -> ex.getName().equals(nameEx))) throw new GymNotesException();
		Exercise e = this.getAllExercise(email).stream().filter( ex -> ex.getName().equals(nameEx)).findAny().get();
		return c.getWorkouts().stream().filter( w -> w.exercises.contains(e)).map(s ->s.kg.get(s.exercises.indexOf(e)) + " " + s.effReps.get(s.exercises.indexOf(e))).collect(toList());
	}
	
	public double avgMaxTon(String exercise) throws GymNotesException{
		if(!exercises.containsKey(exercise)) throw new GymNotesException();
		Exercise e = this.exercises.get(exercise);
		if(!e.needKg()) throw new GymNotesException();
		List<Integer> massimi = this.clients.values()
				.stream()
				.filter(Client::isConsenting)
				.map( c -> {
					try {
						return max(getProgression(c.getEmail(), exercise));
					} catch (GymNotesException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					return null;
				})
//				.collect(Collectors.averagingDouble(d -> d));
				.collect(toList());
		return massimi.stream().mapToDouble(c -> c.doubleValue()).average().getAsDouble();
	}
	private int max(List<String> sessioni) {
		return sessioni.stream().mapToInt(c->totKg(c)).max().orElseThrow();
	}
	
	private int totKg(String session) {
		int kg = Integer.parseInt(session.split(" ")[0]);
		session = session.replaceFirst(kg+" ","");
		int tot = 0;
		for(String s:session.split(" ")) {
			tot = tot + kg*Integer.parseInt(s);
		}
		return tot;
	}
	
	public List<String> maxSec(String exercise) throws GymNotesException {
		if(!exercises.containsKey(exercise)) throw new GymNotesException();
		Exercise e = this.exercises.get(exercise);
		if(e.needKg()) throw new GymNotesException();
		Map<String, Integer> mappa = this.clients.values()
				.stream()
				.filter(c -> c.getWorkouts().stream().flatMap( v-> v.exercises.stream()).collect(toList()).contains(e))
				.collect(
						Collectors.toMap( 
								Client::getName, 
								c -> c.getWorkouts()
								.stream()
								.filter(v -> v.exercises.contains(e))
								.map(v -> v.effReps.get(v.exercises.indexOf(e)))
								.flatMap(v -> Stream.of(v.split(" ")))
								.mapToInt(v -> Integer.parseInt(v))
								.max().getAsInt()));
		return mappa.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
				.map(c -> c.getKey() + " "+ c.getValue()).collect(toList());
		
	}
}






