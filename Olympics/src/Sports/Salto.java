package Sports;

import java.util.Comparator;
import java.util.regex.Pattern;

import Olympics.Sport;

public class Salto extends Sport {
	
	private Pattern p = Pattern.compile("[0-9]?[0-9]?[0-9]");
	
	private static final String CLASSNAME = "salto";
	
	public Salto(String name) throws Exception{
		super(name);
		if(!name.toLowerCase().contains(CLASSNAME)) throw new Exception();
	}
	
	
	
	@Override
	public boolean checkResult(String result) { 
		return result.matches(p.pattern());
	}
	
	
	@Override
	public Comparator<String> comparatorResults() {
		return new saltoComparator();
	}
	
	private static class saltoComparator implements Comparator<String>{
		@Override
        public int compare(String s1, String s2) {
            return Double.compare(Double.parseDouble(s2), Double.parseDouble(s1));
        }
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}
}
