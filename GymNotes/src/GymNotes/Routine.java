package GymNotes;

import java.util.LinkedList;
import java.util.List;

public class Routine {
	private String name;
	protected List<Exercise> exercises = new LinkedList<>();
	protected List<String> rep = new LinkedList<String>();

	public Routine(String name) {
		this.name = name;
	}
	
	public void addExecise(Exercise e, String rep) {
		this.exercises.add(e);
		this.rep.add(rep);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		int i = 0;
		StringBuffer output = new StringBuffer();
		for(Exercise e: exercises) {
			output.append(e + ":"+rep.get(i) + e.getType()  +"\n");
			i++;
		}
		output.deleteCharAt(output.length()-1); // elimina l'ultimo \n
		return output.toString();
	}
}

