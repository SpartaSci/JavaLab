package Autostop;
public class Driver extends User{

	private String plate;
	private int N;
	
	public Driver(String name, String email, int n, String plate) {
		super(name,email);
		this.plate = plate;
		this.N = n;
	}
	
	public String getPlate() {
		return plate;
	}
	public int getN() {
		return N;
	}
}
