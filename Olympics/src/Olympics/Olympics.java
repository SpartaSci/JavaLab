package Olympics;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import java.util.stream.Collectors;


public class Olympics {

	
	private TreeMap<String, Athlete> athletes = new TreeMap<String, Athlete>();
	private HashMap<String, Integer> nationality = new HashMap<String, Integer>(); // <ITA, N_atleti>
	private HashMap<String, Sport> sports = new HashMap<String, Sport>();  // <100 metri, Corsa> in questo modo basta creare nuove classi sport senza modificare Olympics
	
	
	// R1 Sport
	public void addSport(Sport sport) {
		this.sports.put(sport.getName(), sport);
	}
	public List<String> getListofSport(){
			return this.sports.values().stream().map(Sport::getName).collect(Collectors.toList());
	}
	
	// R2 gestione atleti
	public String addAthlete(String name, String nationality, String sport) {
		if(!this.sports.containsKey(sport)) {
			//System.out.println("Sport non presente");
			return "";
		}
		
		if(nationality.length()!=3 || !nationality.equals(nationality.toUpperCase())) {
			return "";
		}
		
		int n;
		String code;
		if(this.nationality.containsKey(nationality)) {
			n = this.nationality.get(nationality);
			n++;
			code = nationality + n;
			this.nationality.replace(nationality, n);
		}else {
			n = 1;
			code = nationality + n;
			this.nationality.put(nationality, n);
		}
		
		Athlete at = new Athlete(code, nationality, name, sports.get(sport));
		this.athletes.put(code, at);
		return code;
	}
	public List<Athlete> getListAthletesByNationality(String nationality) {
		return this.athletes.values().stream().filter(c -> c.getNationality() == nationality).collect(Collectors.toList());
	}
	public List<Athlete> getListAthletesBySport(String sport){
		return this.athletes.values().stream().filter(c -> c.getSport().getName() == sport).collect(Collectors.toList());
	}
	public Athlete getAhlete(String code) {
		return this.athletes.get(code);
	}
	
	
	// R3 Gare
	public void addResult(String sport, String athlete, String result) throws Exception{
		if(!this.sports.containsKey(sport)) throw new Exception();
		if(!this.athletes.containsKey(athlete)) throw new Exception();
		Athlete a = this.athletes.get(athlete);
		Sport s = this.sports.get(sport);
		if(a.getSport()!=s) throw new Exception();
		
		if(s.checkResult(result)) {
			s.addResult(athlete, result);
		}else {
			throw new Exception();
		}
		
	}
	
	
	
	// R4 statistiche 
	
	// lista degli atleti in ordine di risultato
	public List<String> bestResult(String sport){
		if(!sports.containsKey(sport)) return null;
		Comparator<String> comparator = this.sports.get(sport).comparatorResults();
		return this.sports.get(sport).getResults().entrySet().stream()
															 .sorted(Comparator.comparing(Entry::getValue, comparator))
															 .map(e -> e.getKey()+":"+ e.getValue())
															 .collect(Collectors.toList());
	}
	
	public List<String> resultByNazionality() { //ita1 sport result
		
		return this.sports.values().stream().flatMap(c->c.getResults().entrySet().stream())
															 .map(c -> c.getKey() +" "+ this.athletes.get(c.getKey()).getSport() +" "+ c.getValue())
															 .sorted()
															 .collect(Collectors.toList());
	}
	 
	
	
			   //Nazionali Sport 	atleti
	public Map<String, Map<String, List<String>>> mappaNazionali_sport_atleti(){
		
		return this.athletes.values().stream()
					.collect( Collectors.groupingBy(Athlete::getNationality, 
													TreeMap::new, 
													Collectors.groupingBy(a -> a.getSport().getName(),
																		  TreeMap::new,
																		  Collectors.mapping(Athlete::getName, Collectors.toList()))));
		
	}
	
	public Map<String, Double> avgNationBySport(String sport){
		if(!this.sports.containsKey(sport)) return null;
		return this.sports.get(sport).getResults().entrySet().stream()
													  .collect(Collectors.groupingBy(c -> this.athletes.get(c.getKey()).getNationality(),
															  						 TreeMap::new,
															  						 Collectors.collectingAndThen(Collectors.averagingDouble(c -> Double.parseDouble(c.getValue())), d -> (int) (d * Math.pow(10, 2)) / Math.pow(10, 2))));
															  						 /*Collectors.averagingDouble(c -> Double.parseDouble(c.getValue()))));*/
	}
	

	
}
