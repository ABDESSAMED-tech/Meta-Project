package Partie2.Algorithme;

import Partie2.Algorithme.Particle;
import src.Puzzle;
import java.util.LinkedList;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;

public class PSO {
	
	public  SortedSet<Particle> swarm = new TreeSet<Particle>() ;
	public int MAX_SWARM_SIZE ;
	public float c1, c2, w ;
	public int nb_iteration;
	public int swarm_size ;
	
	public Particle generateParticle() {
		int idx = 0;
		Puzzle p = new Puzzle(Particle.INITIAL_STATE.getState()) ;
		String path = "";
		LinkedList<Character> possible_moves ;
		for (int i = 0 ; i < 10 ; i++ ) {
			possible_moves = p.getPossibleMoves() ;
			idx = ThreadLocalRandom.current().nextInt(possible_moves.size())  ;
			path = path + possible_moves.get(idx) ;
			p = p.goToState("" + possible_moves.get(idx)) ;
		}
		return new Particle(p, path) ;
	}
	
	public void initSwarm() {
		while(swarm.size() < MAX_SWARM_SIZE ) {
			swarm.add(generateParticle()) ;
		}
	}
	
	public String runPSO( int MAX_ITER) {
		SortedSet<Particle> new_swarm ;
		
		for(nb_iteration = 0 ; nb_iteration < MAX_ITER ; nb_iteration++) {
			Particle.GBEST = swarm.first().getPosition() ;
			Particle.GBEST_FITNESS = swarm.first().calcFitness() ;
			if(Particle.GBEST_FITNESS == 0) {
				//System.out.println("SOLUTION FOUND ON swarm size "  + swarm_size/*+ nb_iteration + " " + swarm.first() + " " + swarm.first().getPath()*/);
				return swarm.first().getPath();
			}
			new_swarm = new TreeSet<Particle>() ;
			for(Particle p : swarm) {
				new_swarm.add(p.update(w, c1, c2)) ;
			}
			swarm = new_swarm ;
			swarm_size = swarm.size() ;
		}
		return "X" ;
	}
	
	
	public static void main(String[] args) {
		PSO s = new PSO() ;
		Particle.INITIAL_STATE = new Puzzle(349761852) ;
		s.MAX_SWARM_SIZE = 100 ;
		s.w = 0.7f ;
		s.c1 = 0.3f;
		s.c2 =  0.6f ;
                
		
		 long starttime = 0, endtime = 0;
                 starttime = System.currentTimeMillis();
		s.initSwarm() ;
		System.out.println(s.swarm);
		String po = s.runPSO(15000);	
		endtime = System.currentTimeMillis();
                 
		System.out.println(Particle.INITIAL_STATE.goToState(po) );
                System.out.println("temp d'execution :"+(endtime-starttime));
               
	}
	
}
