package Pokemon;

import java.util.LinkedList;
import java.util.List;

public class Pokemon {
	private int num;
	private String name;
	private String type1;
	private String type2;
	private int total;
	private int hp;
	private int atk;
	private int def;
	private int spAtk;
	private int spDef;
	private int spd;
	
	private boolean ismega = false;
	
	private List<Pokemon> mega = new LinkedList<>();
	
	public Pokemon(int num, String name, String type1, String type2, int total, int hp, int atk, int def, int spAtk, int spDef,
			int spd) {
		this.num = num;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.total = total;
		this.hp = hp;
		this.atk = atk;
		this.def = def;
		this.spAtk = spAtk;
		this.spDef = spDef;
		this.spd = spd;
	}

	public String getName() {
		return name;
	}

	public String getType1() {
		return type1;
	}

	public String getType2() {
		return type2;
	}

	public int getTotal() {
		return total;
	}

	public int getHp() {
		return hp;
	}

	public int getAtk() {
		return atk;
	}

	public int getDef() {
		return def;
	}

	public int getSpAtk() {
		return spAtk;
	}

	public int getSpDef() {
		return spDef;
	}

	public int getSpd() {
		return spd;
	}

	public int getNum() {
		return num;
	}
	public void addMega(Pokemon p) {
		this.mega.add(p);
		p.setMega();
	}
	
	private void setMega() {
		this.ismega=true;
	}
	public boolean isMega() {
		return ismega;
	}
	
	public List<Pokemon> getMega(){
		return this.mega;
	}
	
	public int getStatByName(String stat) {
		switch (stat) {
		case "Total": 
			return this.total;
		case "Hp": 
			return this.hp;
		case "Atk": 
			return this.atk;
		case "Def": 
			return this.def;
		case "Sp.Atk": 
			return this.spAtk;
		case "Sp.Def": 
			return this.spDef;
		case "Speed": 
			return this.spd;
		default:
			return 0;
		}
	}
	
	public boolean oneType() {
		if(type2.equals("")) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return this.name +""+ (this.mega.size()!=0 ? this.mega.toString():"");
	}	
	
}
