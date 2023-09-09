package Pomodoro;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Course {
	
	private String code;
	private String name;
	private String classe;
	private int year;
	private TreeMap<String,Topic> topics = new TreeMap<String,Topic>();
	
	public Course(String name, String classe, int year) {
		this.name = name.toLowerCase();
		this.classe = classe.toLowerCase();
		this.year = year;
		this.code = this.createCode();
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getClasse() {
		return classe;
	}

	public int getYear() {
		return year;
	}
	
	private String createCode() {
		
		StringBuffer code = new StringBuffer();
		code.append(this.classe.substring(0, 1).toUpperCase()+this.classe.substring(1));
		code.append(this.year);
		
		List.of(name.split(" ")).forEach(c -> code.append( c.substring(0, 1).toUpperCase()+c.substring(1) ));

		return code.toString();
		
	}
	
	
	public void addTopic(Topic topic) throws PomodoroException {
		if(this.topics.containsKey(topic.getName())) throw new PomodoroException();
		this.topics.put(topic.getName(), topic);
		
	}
	public Topic getTopic(String name) throws PomodoroException {
		if(!this.topics.containsKey(name)) throw new PomodoroException();
		return this.topics.get(name);
	}
	public List<String> getTopics()  {
		return this.topics.keySet().stream().collect(Collectors.toList());
	}
	
	public List<String> getQuiz(){
		return this.topics.values().stream().flatMap(c -> c.getQuiz().stream()).collect(Collectors.toList());
	}
	public int answerQuiz(String ...answer) throws PomodoroException {
		if(this.getQuiz().size() != answer.length) throw new PomodoroException();
		
		List<String> correct = this.topics.values().stream().flatMap(c -> c.correctAnswer().stream()).collect(Collectors.toList());
		int i=0;
		int punti = 0;
		for(String a: answer) {
			if(a == correct.get(i)) punti++;
			i++;
		}
		return punti;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.code;
	}
}
