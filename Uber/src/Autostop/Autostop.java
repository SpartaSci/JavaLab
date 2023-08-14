package Autostop;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Autostop {
	public static enum TravelStatus { OPEN, READY, DONE }	
	
	private Map<String,User> users = new HashMap<String,User>();
	private Map<String,Driver> drivers = new HashMap<String,Driver>();
	private List<Travel> travels = new LinkedList<Travel>();
		
	
	// R1 User
	
	public void addUsers(String name, String email) throws AutostopException {
		if(users.containsKey(email) || drivers.containsKey(email)) {
			throw new AutostopException("email già presente");
		}
		users.put(email, new User(name, email));
		
	}
	public void addDrivers(String name, String email, int seats, String id) throws AutostopException{
		if(users.containsKey(email) || drivers.containsKey(email)) {
			throw new AutostopException("email già presente");
		}
		if( drivers.values().stream().filter(d -> d.getPlate()==id).count() != 0) {
			throw new AutostopException("targa già presente");
		}
		drivers.put(email, new Driver(name, email, seats, id));
	}
	public void addCar(String email, int seats, String plate) throws AutostopException {
		if(!users.containsKey(email)) throw new AutostopException();
		User u = users.remove(email);
		this.addDrivers(u.getName(), email, seats, plate);
	}
	
	public List<String> getDrivers() {
		return this.drivers.keySet().stream().collect(Collectors.toList());
	}
	
	// R2 viaggi
	public void openTravelSession(String driver, String time, String ...stages) throws AutostopException {
		if(!drivers.containsKey(driver)) throw new AutostopException("Driver non presente");
		
		if(this.travels.stream().anyMatch(c -> c.getDriver()==driver && !c.isDone())) {
			throw new AutostopException("Ha già sessioni aperte");
		}
		
		
		Driver d = drivers.get(driver);
		Travel t = new Travel(driver,d.getN(),time,stages);
		travels.add(t);
		return ;
	}
	
	public List<String> getTravel() {
		return this.travels.stream()
				.filter(Travel::isOpen)
				.map(c-> c.getDriver() + " - " + c.getN_available() +" - "+ this.formattedStages(c))
				.collect(Collectors.toList());
	}
	
	private String formattedStages(Travel travel) {
		List<String> stages = travel.getStages();
		return stages.stream().collect(Collectors.joining(" - "));
	}
	
	public void bookTravel(String driver, String user, String stage) throws AutostopException {
		Driver d = this.drivers.get(driver);
		User u = this.users.get(user);
		Travel t = this.travels.stream()
				.filter(Travel::isOpen)
				.filter(c -> c.getDriver()==driver)
				.findAny().orElseThrow(() -> new AutostopException("Driver non ha sessioni aperte"));
		
		if(!t.getStages().contains(stage)) throw new AutostopException("Tappa non presente");
		if(t.getStages().get(t.getStages().size()-1) == stage) throw new AutostopException("Ultima tappa");
		
		if(this.drivers.containsKey(user)) {
			if(this.travels.stream().anyMatch(c -> !c.isDone() &&  c.getDriver()==user) ) throw new AutostopException("I driver possono partecipare solo se non hanno sessioni aperte");
		}
		if(this.travels.stream().anyMatch(c -> !c.isDone() &&  c.getDriver().contains(user))) throw new AutostopException("User gia iscritto altrove");
			
		if(t.addUser(user,stage) == 0) {
			t.ready();
		} 
		
	}
	public List<String> getUsersByTravel(String driver) throws AutostopException {
		Travel t = this.travels.stream()
				.filter(Travel::isOpen)
				.filter(c -> c.getDriver()==driver)
				.findAny().orElseThrow(() -> new AutostopException("Driver non ha sessioni aperte"));
		return t.getUser();
	}
	public void travelReady(String driver) throws AutostopException {
		Travel t = this.travels.stream()
				.filter(Travel::isOpen)
				.filter(c -> c.getDriver()==driver)
				.findAny().orElseThrow(() -> new AutostopException("Driver non ha sessioni aperte"));
		t.ready();
	}
	public void travelDone(String driver) throws AutostopException {
		Travel t = this.travels.stream()
				.filter(Travel::isReady)
				.filter(c -> c.getDriver()==driver)
				.findAny().orElseThrow(() -> new AutostopException("Driver non ha sessioni pronte"));
		t.done();
	}
	
	
	
	//R4 Stats
	
	public List<String> numTravelforPeople() {
		return this.travels.stream()
					.filter(Travel::isDone)
					.flatMap(c -> c.getUser().stream())
					.collect(Collectors.groupingBy(c->c, TreeMap::new, Collectors.counting()))
					.entrySet().stream()
					.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					.map(Map.Entry::getKey)
					.collect(Collectors.toList());
		
	}
	
	public List<String> topDrivers() {
		
		return this.travels.stream()
					.filter(Travel::isDone)
					.collect(Collectors.groupingBy(
								c -> c.getDriver(), 
								TreeMap::new, 
								Collectors.summingInt(d -> d.getNstages())))
					.entrySet().stream()
					.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
					.map(c -> c.getKey() + " " + this.drivers.get(c.getKey()).getPlate() + " "+ this.numTravelsDoneAsDriver(c.getKey())+":" + c.getValue())
					.collect(Collectors.toList());
		
	}
	
	private long numTravelsDoneAsDriver(String email) {
		return this.travels.stream().filter(c -> c.isDone() && c.getDriver()==email).count();
	}
	
	public List<String> travelByEmail(String email){
		return this.travels.stream().filter(c -> c.getDriver()==email || c.getUser().contains(email))
					.map(Travel::toString)
					.collect(Collectors.toList());
	}
	
	
	
	
	
	
	
}
