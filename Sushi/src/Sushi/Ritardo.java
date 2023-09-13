package Sushi;

public class Ritardo {
	
	private int valore;
	
	public Ritardo() {
		valore = 10;
	}
	
	public void increment() {
		this.valore++;
	}
	
	public void decrement() {
		this.valore--;
	}
	
	public int getValore() {
		return valore;
	}
}
