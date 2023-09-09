package Pomodoro;

public class File {
	
	private String name;
	private String text;
	private int rating;
	private Topic topic;
	private String student;
	
	public File(String name, String text) {
		this.name = name;
		this.text = text;
		this.rating=0;
	}
	
	protected void setStudent(String s) {
		this.student = s;
	}
	protected String getStudent() {
		return student;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Topic getTopic() {
		return topic;
	}
	protected void up() {
		rating++;
	}
	protected void down() {
		rating--;
	}
	public int getRating() {
		return rating;
	}
	
	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	
	
	
}
