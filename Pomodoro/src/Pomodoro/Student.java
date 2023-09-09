package Pomodoro;

import java.util.HashMap;
import java.util.List;

public class Student {
	
	private String number;
	private String name;
	private String surname;
	private HashMap<File,String> ratings = new HashMap<File, String>();
	private List<File> files;
	
	public Student(String number, String name, String surname) {
		this.number = number;
		this.name = name;
		this.surname = surname;
	}

	public void addFile(File f) {
		this.files.add(f);
	}
	
	public String getNumber() {
		return number;
	}


	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}
	
	public void addRating(File f, String rating) {
		this.ratings.put(f, rating);
	}
	public String alreadyRated(File f) {
		return this.ratings.getOrDefault(f, "");
	}
}
