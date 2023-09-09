package GymNotes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Workout extends Routine{
	
	protected List<String> effReps = new LinkedList<String>();
	protected List<Integer> kg = new LinkedList<>();
	private boolean Ongoing = true;
	
	public Workout(Routine routine) {
		super(routine.getName());
		this.rep = new LinkedList<String>(routine.rep);
		this.exercises = new LinkedList<>(routine.exercises);
	}
	
	public boolean isOpen() {
		return Ongoing;
	}
	public void endW() {
		this.Ongoing = false;
	}
	public void addExecise(int kg, String rep) {
		effReps.add(rep);
		this.kg.add(kg);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return super.getName();
	}
	@Override
	public String toString() {
		int i = 0;
		StringBuffer output = new StringBuffer();
		for(Exercise e: exercises) {
			output.append(e + ":"+effReps.get(i) + e.getType()  +"\n");
			i++;
		}
		output.deleteCharAt(output.length()-1); // elimina l'ultimo \n
		return output.toString();
	}
}
