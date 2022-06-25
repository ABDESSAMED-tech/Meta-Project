package Partie2.Algorithme;

import src.Puzzle;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;   

public class Particle implements Comparable<Particle> {
	public static Puzzle INITIAL_STATE ; //Puzzle state to start off from.
	
	public static Puzzle GBEST ;
	public static int GBEST_FITNESS = Integer.MAX_VALUE ;
	public static String[] CYCLES = {"URDL", "RDLU", "DLUR", "LURD", "LDRU", "DRUL", "RULD", "ULDR", "LR", "RL", "UD", "DU"} ;
	public static HashMap<Character, Character> OPPOSITES ;
	
	static {
		OPPOSITES = new HashMap<Character, Character>() ;
		OPPOSITES.put('U', 'D') ;
		OPPOSITES.put('D', 'U') ;
		OPPOSITES.put('L', 'R') ;
		OPPOSITES.put('R', 'L') ;
 	}
	
	private Puzzle position ;
	private int PBEST ;
	private int PBEST_FITNESS ;
	private int fitness ; 
	private float velocity ;
	private String path ;
		
	public Particle() {	
	}
	
	public Particle(Puzzle p, String path ) {
		position = p ;
		fitness = calcFitness() ;
		velocity = 0;
		this.path = path;
		PBEST = position.getState();
		PBEST_FITNESS = fitness ;
		
	}
	
	public int calcFitness() {
		return position.getReversalCount() + position.manhattan() ; 
	}
	
	public int localDist() {
		return position.manhattan(getPBEST()) ;	
	}
	
	public int globalDist() {
		return position.manhattan(GBEST.getState()) ;
	}
	
	public Particle update(float w, float c1, float c2) {
		int local_dist = localDist() ;
		int global_dist = globalDist() ;
		float r1 = ThreadLocalRandom.current().nextFloat() ;
		float r2 = ThreadLocalRandom.current().nextFloat();
		LinkedList<Character> possible_moves = position.getPossibleMoves();
		
		
		if(!path.equals("")) possible_moves.remove(OPPOSITES.get(path.charAt(path.length() - 1))) ;
		if(possible_moves.size() > 0) {
			velocity = (velocity*w  + c1*r1*local_dist + c2*r2*global_dist) % possible_moves.size()	;
			path = path + possible_moves.get((int) velocity) ;
			position  = INITIAL_STATE.goToState(path) ;
			fitness = calcFitness() ;
			if(fitness < PBEST_FITNESS) {
				PBEST = position.getState() ;
				PBEST_FITNESS = fitness ;
			}
		}
		
		Particle p = new Particle() ;
		p.path = path ;
		p.position = new Puzzle(position.getState()); 
		p.PBEST = PBEST ;
		p.PBEST_FITNESS = PBEST_FITNESS ;
		p.velocity = velocity ;
		p.fitness = fitness ;
		if(path.length() >= 32) p.truncate();
		return p ;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false ;
		if(o == this) return true ;
		if(!(o instanceof Particle)) return false ;
		
		Particle c = (Particle) o ; 
		return path.equals(c.path) ;
	}
	
	public int compareTo(Particle p) {
		int diff = fitness - p.fitness ;
		if(equals(p)) return 0 ;
		
		return (diff != 0) ? diff : -1;
	}
	
	public void truncate() {
		for(int i = 0 ; i < 12 ; i++ ) {
			path = path.replaceAll(CYCLES[i], "") ; 
		}
	}
	
	public String toString() {
		return position.getState() + " " + fitness ;
		
	}
	
	public Puzzle getPosition() {
		return position ;
	}

	public String getPath() {
		return path;
	}

	public int getPBEST() {
		return PBEST;
	}

	public int getPBEST_FITNESS() {
		return PBEST_FITNESS;
	}

	
}
