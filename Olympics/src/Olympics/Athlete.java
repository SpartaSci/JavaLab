package Olympics;

public class Athlete {
	
	private String nationality;
	private String id; //ITA1
	private String name; // nome e cognome
	private Sport sport;
	public Athlete(String id, String nationality, String name, Sport sport) {
		this.id = id;
		this.nationality = nationality;
		this.name = name;
		this.sport = sport;
	}

	public String getNationality() {
		return nationality;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Sport getSport() {
		return sport;
	}
	
	@Override
	public String toString() {
		return id + ":" + name;
	}
	
	
	
}
