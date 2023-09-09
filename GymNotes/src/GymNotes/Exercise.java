package GymNotes;

public class Exercise {
	private String name;
	private boolean bodyWeight;
	private String equipment;
	private String type;
	
	private boolean base = true;
	
	public Exercise(String name, boolean bodyWeight, String equipment, String type) {
		super();
		this.name = name;
		this.bodyWeight = bodyWeight;
		this.equipment = equipment;
		this.type = type;
	}
	
	public void personal() {
		base = false;
	}

	public String getName() {
		return name;
	}

	public boolean isBodyWeight() {
		return bodyWeight;
	}

	public String getEquipment() {
		return equipment;
	}

	public String getType() {
		return type;
	}
	public boolean needKg() {
		return type.equals("rep");
	}

	public boolean isBase() {
		return base;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
