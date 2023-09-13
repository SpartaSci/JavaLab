package Sushi;

import java.awt.FlowLayout;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;


public class AllYouCanEat extends Thread{
	
	public static final int NUM_PIATTI  = 50;
	public static final int PIATTI_PER_PERSONA  = 2;
	public static final int COPERTI_PER_TAVOLO  = 2;
	
	
	private int N;
	private boolean[] freeTable;
	private List<Tavolo> tables = new LinkedList<Tavolo>();
	private  List<Ordine> ready_for_kitchen = Collections.synchronizedList(new LinkedList<Ordine>());
	private List<Ordine> ready_for_serving = Collections.synchronizedList(new LinkedList<Ordine>());
	private List<Ordine> completed = Collections.synchronizedList( new LinkedList<Ordine>());

	
	
	protected Cucina kitchen;
	protected Sala sala;

	public JFrame pannello = new JFrame("Sushi");
	
	public AllYouCanEat(int N) {
		this.N = N;
		freeTable = new boolean[N];
		kitchen = new Cucina(this);
		sala = new Sala(this);
		for(int i=0; i<N; i++) {
			freeTable[i] = true;
		}
		
		pannello.setSize(400, 200);
		pannello.setLayout(new FlowLayout());
		pannello.setVisible(true);
		pannello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void run() {
		this.setName("AllYouCanEat"); //rinominare per aiutare debug
		kitchen.setName("cucina");
		kitchen.start();
		sala.setName("sala");
		sala.start();
		while(true) {
			System.out.println("||||||||||||||||||||||||||||||");
			System.out.println(this.stringOrderKitchen());
			System.out.println(this.stringOrderServing());
			System.out.println(this.stringOrderCompleted());
			System.out.println("||||||||||||||||||||||||||||||");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Tavolo newTable(int coperti) {
		
		int tableNeeded = coperti/COPERTI_PER_TAVOLO;
		if(this.getNumFreeTable()<tableNeeded) {
			return null;
		}
		int id = getFirstFreeTable();
		if(id==-1) return null;
		Tavolo table = new Tavolo(coperti, id, this);
		for(int i=0; i<tableNeeded-1;i++) {
			int n = getFirstFreeTable();
			if(n==-1) return null;
			table.addNumber(n);
		}
		this.tables.add(table);
		table.start();
		return table;
	}
	private int getFirstFreeTable() {
		for(int j=0; j<N; j++) {
			if(freeTable[j] == true) {
				freeTable[j] = false;
				return j++;
			}
		}
		return -1;
	}
	private int getNumFreeTable() {
		int i = 0;
		for(int j = 0;j<N;j++) {
			if(freeTable[j]) {
				i++;
			}
		}
		return i;
	}

	protected void sendOrderToKitchen(Ordine order) {
		this.ready_for_kitchen.add(order);
	}
	protected void orderReadyForServing(Ordine order) {
		this.ready_for_serving.add(order);
	}
	protected void orderCompleted(Ordine order) {
		this.completed.add(order);
	}
	protected Ordine getOrderForKitchen() {
		if(ready_for_kitchen.isEmpty()) return null;
		return this.ready_for_kitchen.remove(0);
	}
	protected Ordine getOrderForServing() {
		if(ready_for_serving.isEmpty()) return null;
		return this.ready_for_serving.remove(0);
	}
	public String stringOrderKitchen() {
		StringBuffer b = new StringBuffer();
		b.append("Order for kitchen\n");
		this.ready_for_kitchen.forEach(c -> b.append(c.toString() + "\n"));
		return b.toString();
	}
	public String stringOrderServing() {
		StringBuffer b = new StringBuffer();
		b.append("Order for serving\n");
		this.ready_for_serving.forEach(c -> b.append(c.toString() + "\n"));
		return b.toString();
	}
	public String stringOrderCompleted() {
		StringBuffer b = new StringBuffer();
		b.append("Order completed\n");
		this.completed.stream().skip(completed.size()>10?(completed.size()-10):0).forEach(c -> b.append(c.toString() + "\n"));
		return b.toString();
	}
}
