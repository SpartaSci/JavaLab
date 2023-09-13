package Sushi;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Tavolo extends Thread{
	private AllYouCanEat sushi;
	private int number;
	private int coperti;
	private List<Integer> otherNumber = new LinkedList<>();
	private List<Ordine> orders = new LinkedList<Ordine>();
	private Ordine currentOrder;
	private boolean aperto = true;
	
	private Ritardo ritardo = new Ritardo();
	private int n = ritardo.getValore();
	private Pulsanti pulsante;
	
	private Random rand = new Random();
	
	protected Tavolo(int coperti, int number, AllYouCanEat sushi) {
		this.number = number;
		this.coperti = coperti;
		this.sushi = sushi;
		currentOrder = new Ordine(this);
		pulsante = new Pulsanti(ritardo, "Tavolo"+number);
		sushi.pannello.add(pulsante);
	}
	
	@Override
	public void run() {
		while(aperto) {
			for(int j=0; j< (rand.nextInt(AllYouCanEat.PIATTI_PER_PERSONA*this.coperti-1)+1); j++) {			
				this.addSushi( rand.nextInt(AllYouCanEat.NUM_PIATTI));
			}
			if(this.currentOrder.getOrder().size()!=0)this.sendOrder();
			try {
				n = ritardo.getValore();
				Thread.sleep(n*1000);
			}catch (Exception e) {}
		}
	}
	public int getNumber() {
		return number;
	}
	protected List<Integer> getNumbers(){
		List<Integer> r = new LinkedList<Integer>();
		r.add(number);
		r.addAll(otherNumber);
		return r;
	}
	
	protected void addNumber(int n) {
		if(n==number) return ;
		this.otherNumber.add(n);
	}
	
	public void addSushi(int sushi) {	
		this.currentOrder.addSushi(sushi);
	}
	
	public void sendOrder() {
		this.orders.add(currentOrder);
		this.sushi.sendOrderToKitchen(currentOrder);
		currentOrder = new Ordine(this);
	}
	
	public String stringa() {
		StringBuffer b = new StringBuffer();
		b.append("Table "+this.number+"(");
		this.otherNumber.forEach(c -> b.append(c+","));
		b.deleteCharAt(b.length()-1).append(")");
		return super.toString();
	}
	
}