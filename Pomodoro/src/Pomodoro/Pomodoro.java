package Pomodoro;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
public class Pomodoro {
	
	
	private TreeMap<String,Student> students = new TreeMap<String, Student>();
	private TreeMap<String,Course> courses = new TreeMap<String, Course>();
	private List<Topic> topics = new LinkedList<Topic>();
	
	// R1 
	public void addStudent(String number, String name, String surname) throws PomodoroException {
		
		if(students.containsKey(number)) throw new PomodoroException();
		Student student = new Student(number, name, surname);
		this.students.put(number, student);
	}
	
	public Course addCourse(String classe, String name, int year) throws PomodoroException {
		Course course = new Course(name, classe, year);
		if(courses.containsKey(course.getCode())) throw new PomodoroException();
		this.courses.put(course.getCode(), course);
		return course;
	}
	public List<String> getCourses(){
		return this.courses.keySet().stream().collect(Collectors.toList());
	}
	public Course getCourse(String code) throws PomodoroException{
		if(!this.courses.containsKey(code)) throw new PomodoroException();
		return this.courses.get(code);
	}
	
	// R2 
	public Topic addTopic(String course, String topic) throws PomodoroException {
		String topi = topic.toLowerCase();
		if(!this.courses.containsKey(course)) throw new PomodoroException();
		
		Course cou = this.courses.get(course);
		Topic top = new Topic(topi, cou,this);
		cou.addTopic(top);	
		topics.add(top);
		return top;
	}
	protected boolean checkStudent(String code) {
		return this.students.containsKey(code);
	}
	
	// R3 
	public void ratingUp(File file, String number) throws PomodoroException {
		if(!this.students.containsKey(number)) throw new PomodoroException();
		Student student = this.students.get(number);
		if(student.alreadyRated(file) == "+") {
			return;
		}
		if(student.alreadyRated(file) == "-") {
			file.up();
		}
		student.addRating(file, "+");
		file.up();
	}
	
	public void ratingDown(File file, String number) throws PomodoroException {
		if(!this.students.containsKey(number)) throw new PomodoroException();
		Student student = this.students.get(number);
		if(student.alreadyRated(file) == "-") {
			return;
		}
		if(student.alreadyRated(file) == "+") {
			file.down();
		}
		student.addRating(file, "-");
		file.down();
	}
	
	// R4 
	
	public List<String> getQuiz(){
		return this.courses.values().stream().flatMap(c-> c.getQuiz().stream()).collect(Collectors.toList());
	}
	
	// R5
	public List<String> topRated(){
		return this.topics.stream()
				.flatMap(c -> c.getFiles().stream())
				.sorted(Comparator.comparing(c->c.getRating(), Comparator.reverseOrder()))
				.map(c -> c.getRating() +":"+ c.getName() + "|"+c.getTopic().getCourse().getName())
				.limit(10)
				.collect(Collectors.toList());
	}
	
	public Map<String, Map<String, List<String>>> allTopics(){
		
		return this.courses.values().stream()
							 .collect(Collectors.groupingBy(Course::getClasse,
									 						Collectors.toMap(Course::getName,
									 										Course::getTopics)));

		
	}
	
	public Map<String, List<String>> filesByStudent(){
		return this.topics.stream().flatMap(c -> c.getFiles().stream())
							.collect(Collectors.groupingBy(
									File::getStudent,
									Collectors.mapping(c -> c.getName(), Collectors.toList())));
	} 
}
