package Pomodoro;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Topic {
	
	private String name;
	private Course course;
	private HashMap<String,File> files = new HashMap<String,File>();
	private LinkedList<Quiz> quiz = new LinkedList<Quiz>();
	private Pomodoro pomodoro;
	
	public Topic(String name, Course course, Pomodoro pomodoro) {
		this.name = name;
		this.course = course;
		this.pomodoro = pomodoro;
	}
	public class Quiz {
		public String question;
		public String answer;
		public Quiz(String question, String answer) {
			this.question = question;
			this.answer = answer;
		}	
	}
	public String getName() {
		return name;	
	}
	public Course getCourse() {
		return course;
	}
	
	public String addFile(File originale, String s) throws PomodoroException {
		if(!this.pomodoro.checkStudent(s)) throw new PomodoroException();
		File file = new File(originale.getName(), originale.getText());
		
		String newName = file.getName();
		boolean unique = false;
		int i = 1;
		while(!unique) {
			if(this.files.containsKey(newName)) {
				newName = file.getName() + "("+i+")";
				i++;
			}else {
				unique = true;
			}
		}
		file.setStudent(s);
		file.setName(newName);
		file.setTopic(this);
		this.files.put(newName, file);
		return newName;
	}
	
	public List<String> getFilesName() {
		return files.keySet().stream().collect(Collectors.toList());
	}
	public List<File> getFiles(){
		return this.files.values().stream().collect(Collectors.toList());
	}
	public File getFile(String name) throws PomodoroException{
		if(!this.files.containsKey(name)) throw new PomodoroException();
		return this.files.get(name);
	}
	
	public void addQuiz(String question, String answer) {
		Quiz qui = new Quiz(question, answer);
		this.quiz.add(qui);
	}
	
	public List<String> getQuiz(){
		return this.quiz.stream().map(c -> c.question).collect(Collectors.toList());
	}
	protected List<String> correctAnswer(){
		return this.quiz.stream().map(c -> c.answer).collect(Collectors.toList());
	}
	public int answerQuiz(String ...answer) throws PomodoroException{
		if(this.getQuiz().size()!=answer.length) throw new PomodoroException();
		
		List<String> correct = this.correctAnswer();
		int i = 0;
		int punti = 0;
		for(String a: answer) {
			if(a == correct.get(i)) punti ++;
			i++;
		}
		return punti;
	}
	
	@Override
	public String toString() {
		return this.name + "|" +this.course.getCode();
	}


}
