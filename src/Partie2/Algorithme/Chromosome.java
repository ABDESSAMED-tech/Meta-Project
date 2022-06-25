package Partie2.Algorithme;

import src.Puzzle;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;   
public class Chromosome implements Comparable<Chromosome>{
	
	public static final HashMap<String, Character> G2M = initG2M() ; //Genes to Moves
	public static final HashMap<Character, String> M2G = initM2G() ; //Moves to Genes
	public int LENGTH ; // Length of each chromosome.
	public static Puzzle INITIAL_STATE ; //Puzzle state to start off from.
	public static String[] CYCLES = {"URDL", "RDLU", "DLUR", "LURD", "LDRU", "DRUL", "RULD", "ULDR", "LR", "RL", "UD", "DU"} ;
	
	private float fitness ;
	private String genes ;
	
	
	private static HashMap<String, Character> initG2M() {
		HashMap<String, Character> moves = new HashMap<String, Character>() ;
		moves.put("00",'U') ;
		moves.put("01",'D');
		moves.put("10",'L');
		moves.put("11",'R');
		return moves ;
	}
	
	private static HashMap<Character, String> initM2G() {
		HashMap<Character, String>  moves = new HashMap<Character, String>() ;
		moves.put('U',"00") ;
		moves.put('D',"01");
		moves.put('L',"10");
		moves.put('R',"11");
		return moves ;
	}
	
	public Chromosome() {}

	public Chromosome(Chromosome c) {
		genes = c.genes ;
		LENGTH = c.LENGTH;
	}
		
	public Chromosome(String path, boolean coded) {
		if (coded)
			this.genes = decode(path) ;
		else
			this.genes = path ;
		LENGTH = genes.length() ;
		fitness = fitManhattan(INITIAL_STATE) ;
		
	}
	
	//for a non coded string, returns its coded binary form. for example RD -> 1101.
	public String encode(String path) { 
		char[] coded = new char[LENGTH*2]; 
		String temp ;
		for(int i = 0 ; i < path.length() ; i++) {
			temp = M2G.get(path.charAt(i)) ;
			coded[2*i] = temp.charAt(0) ;
			coded[2*i + 1] = temp.charAt(1) ;
		}
		return String.valueOf(coded) ;
	}
	
	//returns coded binary form of this chromosome.
	public String encode() {
		char[] coded = new char[LENGTH*2]; 
		String temp ;
		if(genes.length() == 0 || genes.equals("")) return "" ;
		for(int i = 0 ; i < LENGTH ; i++) {
			temp = M2G.get(getGenes().charAt(i)) ;
			coded[2*i] = temp.charAt(0) ;
			coded[2*i + 1] = temp.charAt(1) ;
		}
		return String.valueOf(coded) ;
	}
	
	//for a coded binary string, returns its path form. for example 1101 -> RD.
	public String decode(String genomes) {
		StringBuilder path = new StringBuilder() ;
		for(int i = 0 ; i < genomes.length() / 2  ; i++) {
			path.append(G2M.get("" + genomes.charAt(2*i) + genomes.charAt(2*i + 1)));
		}
		return path.toString() ;
	}
	
	//removes cycles from the chromosome.
	//is called whenever a chromosome length surpasses 32, since no instance of a 3x3 puzzle requires more than 31 moves.
	//Main purpose is to avoid using too much memory, in case we get large populations with long chromosomes.
	public void truncate() {
		for(int i = 0 ; i < 12 ; i++ ) {
			genes = genes.replaceAll(CYCLES[i], "") ; 
		}
		LENGTH = genes.length() ;
	}
	
	//returns the fitness of a chromosome. equal to the inverse of the manhattan distance.
	public float fitManhattan(Puzzle initial_state) {
		Puzzle final_state = INITIAL_STATE.goToState(getGenes()) ;
		if (final_state == null) return 1 ;
		if (final_state.manhattan()+ final_state.getReversalCount() == 0) return 3 ;
		return (float) 1 + 1/ (float)(final_state.manhattan() + final_state.getReversalCount()) ;
	}
	
	//generic mutation function, inverses a random number of genes. 
	//*******ONLY USED WHEN THE SIZE OF THE CHROMOSOME = 1*********
	private void mutate(double mut_rate) {
		double proba ;
		int gene_pos ;	
		char[] genesch = encode().toCharArray() ;
		for (int i = 0 ; i < 2 ; i++) { 
			proba = ThreadLocalRandom.current().nextDouble(0, 1);
			gene_pos = ThreadLocalRandom.current().nextInt(0, LENGTH*2) ;	
			genesch[gene_pos] = proba < mut_rate ? (char) (Math.abs(genesch[gene_pos] - 49) + 48) : genesch[gene_pos];
		}
		genes = decode(String.valueOf(genesch)) ;
		fitness= fitManhattan(INITIAL_STATE);
	}
	
	//Main mutation function. a chromosome has a chance to grow a new genome.
	public void grow(double mut_rate) {
		if (ThreadLocalRandom.current().nextFloat() > mut_rate) return ;
		
		Puzzle p = INITIAL_STATE.goToState(genes) ;
		if(p==null) {
			int rand = ThreadLocalRandom.current().nextInt(0, 4) ;	
			String moves = "UDLR" ;
			genes = getGenes() +""+ moves.charAt(rand)  ;
			this.fitness = fitManhattan(INITIAL_STATE);
			LENGTH++ ;
			return  ;
		}
		
		LinkedList<Character> possible_moves = p.getPossibleMoves();
		int idx=0;

		idx = ThreadLocalRandom.current().nextInt(possible_moves.size()) ;
		genes = getGenes() + possible_moves.get(idx) ;
		this.fitness = fitManhattan(INITIAL_STATE);
		LENGTH++ ;
	}
	
	//Crossover function.
	public Chromosome[] crossOver(Chromosome parent2) {
		if(LENGTH == 0) return new Chromosome[] {new Chromosome(parent2), new Chromosome(parent2)};
		if(parent2.LENGTH == 0)return new Chromosome[] {new Chromosome(this), new Chromosome(this)};
		
		String offspring_a ;
		String offspring_b ;
		
		//if both parents have a size of 1, return their mutated forms. (using generic mutation)
		if(LENGTH == 1 && parent2.LENGTH==1) {  
			Chromosome c1,c2 ;
			c1 = new Chromosome(this) ; c2 = new Chromosome(parent2) ;
			c1.mutate(1); c2.mutate(1);
			return new Chromosome[] {c1,c2} ;
		}
		//if size of parent1 is 1. return parent2[0:-1] + parent1.
		else if(LENGTH == 1) {
			offspring_a = genes ;
			offspring_b = parent2.genes.substring(0, parent2.LENGTH - 1) + genes ;
			return  new Chromosome[] {new Chromosome(offspring_a, false) , new Chromosome(offspring_b, false)}  ;
		}
		//if size of parent2 is 1. return parent1[0:-1] + parent2.
		else if(parent2.LENGTH == 1){
			offspring_a = parent2.genes ;
			offspring_b = genes.substring(0, LENGTH - 1) + parent2.genes ;
			return  new Chromosome[] {new Chromosome(offspring_a, false) , new Chromosome(offspring_b, false)}  ;
		}
		
		int cross_point = ThreadLocalRandom.current().nextInt(1, LENGTH*2 - 1) ;
		int cross_point2 = cross_point;
		
		if(LENGTH != parent2.LENGTH) {
			cross_point2 = ThreadLocalRandom.current().nextInt(1, parent2.LENGTH*2 - 1) ;
		}

		offspring_a = encode().substring(0, cross_point) + parent2.encode().substring(cross_point2) ;
		offspring_b = parent2.encode().substring(0, cross_point2) + encode().substring(cross_point);
		
		if(offspring_a.equals("") || offspring_b.equals("")) {
			System.out.println("EMPTY");
		}
		return  new Chromosome[] {new Chromosome(offspring_a, true) , new Chromosome(offspring_b, true)}  ;
	}
	
	public String toString() {
		return getGenes() ;
	}
	
	public int compareTo(Chromosome C) {
		float diff = (getFitness() - C.getFitness());
		
		if (equals(C)) return 0; 
		else if (diff > 0) return -1 ;
		else return 1 ;
	}
	
	public int hashCode() {
		return getGenes().hashCode() ;
	}
	
	public boolean equals(Object o) {
		
		if (o == null) return false ;
 		if (!(o instanceof Chromosome)) return false ;
 		if (o == this) return true ; 
		
 		Chromosome c = (Chromosome) o ;
		return getGenes().equals(c.getGenes()) ;
	}

	public float getFitness() {
		return fitness;
	}

	public String getGenes() {
		return genes;
	}
}
