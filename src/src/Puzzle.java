package src;
import java.util.LinkedList;


public class Puzzle {
	
	// Number 9 represents the empty tile.
	public static int GOAL = 123456789 ;
	private int state ;
	
	public Puzzle() {
	}
	
	public Puzzle(int state) {
		this.state = state ;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}
	
	//returns the position of the blank tile.
	public int getPosBlank() { 
		return Integer.toString(state).indexOf('9');
	}
	
	//returns a new instance of Puzzle with the blank tile moved up.
	public Puzzle moveUp() {
		int posx = Integer.toString(state).indexOf('9');
		if(posx >= 3 ) {
			char[] newstate =  Integer.toString(state).toCharArray() ;
			newstate[posx] = newstate[posx - 3] ;
            newstate[posx - 3] = '9' ;
            return new Puzzle (Integer.parseInt(String.valueOf(newstate)));
		}
		return null ;
	}
	
	//returns a new instance of Puzzle with the blank tile moved down.
	public Puzzle moveDown() {
		int posx = Integer.toString(state).indexOf('9') ;
		if(posx < 6 ) {
			char[] newstate =  Integer.toString(state).toCharArray() ;
			newstate[posx] = newstate[posx + 3] ;
            newstate[posx + 3] = '9' ;
            return new Puzzle (Integer.parseInt(String.valueOf(newstate)));
		}
		return null ;
	}
	
	//returns a new instance of Puzzle with the blank tile moved left.
	public Puzzle moveLeft() {
		int posx = Integer.toString(state).indexOf('9');
		if(posx % 3 != 0 ) {
			char[] newstate =  Integer.toString(state).toCharArray() ;
			newstate[posx] = newstate[posx - 1] ;
            newstate[posx -1 ] = '9' ;
            return new Puzzle (Integer.parseInt(String.valueOf(newstate)));
		}
		return null ;
	}
	
	//returns a new instance of Puzzle with the blank tile moved right.
	public Puzzle moveRight() {
		int posx = Integer.toString(state).indexOf('9');
		if(posx % 3 != 2 ) {
			char[] newstate =  Integer.toString(state).toCharArray() ;
			newstate[posx] = newstate[posx + 1] ;
            newstate[posx +1 ] = '9' ;
            return new Puzzle (Integer.parseInt(String.valueOf(newstate)));
		}
		return null ;
	}
	
	
	//returns the row of a tile. helper function for manhattan()
	private int row(int tilepos) {
		return ((tilepos - 1) / 3) + 1  ;
	}
	
	//returns the column of a tile. helper function for manhattan()
	private int col(int tilepos) {
		return (tilepos % 3 != 0) ? tilepos % 3 : 3 ;
	}
	
	//returns True if two tiles are adjacent to each other. False otherwise.
	//Helper function for getReversalCount().
	private boolean areAdjacent(int tilepos1, int tilepos2) {
		return ( Math.abs(tilepos1 - tilepos2) == 3 || Math.abs(tilepos1 - tilepos2) == 1 )  ;
	}
	
	//returns the number of direct reversals.
	//two tiles form a direct reversal if :
	//	1. they are adjacent to each other.
	//	2. tile a is in the goal position of tile b, and tile b is in the goal position of tile a.
	//		1	2	3
	//		5	4	6		in this example 5 and 4 form a direct reversal.
	//		7	8
	public int getReversalCount() {
		String statestr = Integer.toString(state);
		String goalstr = Integer.toString(GOAL);
		char charingoal ,charingoal2, charinstate ;
		int posinstate ,reversals = 0 ;
		
		for(int i = 0 ; i < 9 ; i++) {
			charingoal = goalstr.charAt(i);
			charinstate = statestr.charAt(i);
			if (charingoal == '9' || charinstate == '9' || charinstate == charingoal) continue ;
			posinstate = statestr.indexOf(charingoal);
			charingoal2 = goalstr.charAt(posinstate); 
			if ((charinstate == charingoal2) && (charingoal == statestr.charAt(posinstate)) && (areAdjacent(i, posinstate))) reversals++ ;
		}
		return reversals ;
	}
	
	//returns the sum of Manhattan distances between a tile and its approriate position.
	public int manhattan() {
		int manhattan = 0;
		char[] statestr = Integer.toString(state).toCharArray(); 
		String goalstr = Integer.toString(GOAL) ;
		for(int i = 0 ; i < 9 ; i++) {
			if(statestr[i] == '9') continue ;
			int rowdiff = Math.abs(row(statestr[i] - 48) - row(1 + goalstr.indexOf(i + 49))); //48 and 49 represent the ascii code of '0' and '1' respectively.
            int coldiff = Math.abs(col(statestr[i] - 48) - col(1 + goalstr.indexOf(i + 49)));
            manhattan = manhattan + rowdiff + coldiff;
		}
		return manhattan ;
	}
	
	public int manhattan(int goal) {
		int manhattan = 0;
		char[] statestr = Integer.toString(state).toCharArray(); 
		String goalstr = Integer.toString(goal) ;
		for(int i = 0 ; i < 9 ; i++) {
			if(statestr[i] == '9') continue ;
			int rowdiff = Math.abs(row(statestr[i] - 48) - row(1 + goalstr.indexOf(i + 49))); //48 and 49 represent the ascii code of '0' and '1' respectively.
            int coldiff = Math.abs(col(statestr[i] - 48) - col(1 + goalstr.indexOf(i + 49)));
            manhattan = manhattan + rowdiff + coldiff;
		}
		return manhattan ;
	}

	//returns tuple with the vertical distance and horizontal distance separated. (USED IN PART 2)
	public int[] manhattanXY(int goal) {
		char[] statestr = Integer.toString(state).toCharArray(); 
		int rowdiff=0, coldiff =0;
		String goalstr = Integer.toString(goal) ;
		for(int i = 0 ; i < 9 ; i++) {
			if(statestr[i] == '9') continue ;
			rowdiff = rowdiff + Math.abs(row(1 + goalstr.indexOf(i + 49)) - row(statestr[i] - 48)); //48 and 49 represent the ascii code of '0' and '1' respectively.
			coldiff = coldiff + Math.abs(col(1 + goalstr.indexOf(i + 49)) - col(statestr[i] - 48));
		}
		
		return new int[]{coldiff, rowdiff} ;
	}
	
	//returns the number of misplaced tiles (also known as haming distance).
	public int misplaced() {
		int misplaced  = 0 ;
		char[] statestr = Integer.toString(state).toCharArray() ;
		char[] goalstr = Integer.toString(GOAL).toCharArray() ;
		for (int i = 0 ; i < 9 ; i++) {
			if(statestr[i] == '9') continue ;
			if (goalstr[i] != statestr[i]) misplaced ++ ;
		}
		return misplaced ; 
	}
	
	//returns the inversion count of a state.
	//Two tiles form an inversion when the one with the higher value appears before the one with the lower value.
	public int getInversionCount() {
		char[] statestr = Integer.toString(state).toCharArray();
		int invcount = 0 ; 
		for(int i = 0 ; i < 8 ; i++) {
			if(statestr[i] == '9') continue ;
			for(int j = i + 1 ; j < 9 ; j++ ) {
				if(statestr[j]== '9') continue ;
				if(statestr[i] > statestr[j]) invcount++ ;
			}
		}
		return  invcount;
	}
	
	//returns the parity of a state.
	//The parity of a state is the parity of its inversion count.
	public int getParity() {
		return getInversionCount() % 2 ;
 	}
	
	//returns true if the state has a solution, false otherwise.
	//A state has a solution if it has the same parity as the parity of its goal state.
	public boolean isSolvable() {
		Puzzle goal = new Puzzle(GOAL) ;
		return getParity() == goal.getParity() ;
	}
	
	public boolean isSolution() {
		return state == GOAL ;
	}
	
	
	public String[] performMoves(String path){
		Puzzle trace = new Puzzle(state) ;
		String[] moves = new String[path.length()] ;
	    for(int i = 0 ; i<path.length() ; i++){
	        switch (path.charAt(i)){
	        case 'U':
	            trace = trace.moveUp();
	            break;
	        case 'D':
	        	trace = trace.moveDown();
	            break;
	        case 'L':
	            trace = trace.moveLeft() ;
	            break;
	        case 'R':
	            trace = trace.moveRight() ;
	            break;
	        }
	        moves[i] = Integer.toString(trace.state);
	    }
	    
	    return moves ;
    }
	
	public Puzzle goToState(String path) {
		Puzzle trace = new Puzzle(state) ;
		for(int i = 0 ; i<path.length() && trace!=null; i++){
	        switch (path.charAt(i)){
	        case 'U':
	            trace = trace.moveUp();
	            break;
	        case 'D':
	        	trace = trace.moveDown();
	            break;
	        case 'L':
	            trace = trace.moveLeft() ;
	            break;
	        case 'R':
	            trace = trace.moveRight() ;
	            break;
	        }
		}		
		return trace ;
	}
	
	public LinkedList<Character> getPossibleMoves(){
		LinkedList<Character> list = new LinkedList<Character>();
		int posx = Integer.toString(state).indexOf('9');
		if(posx >= 3 ){
			list.add('U');
		}
		if(posx < 6 ) {
			list.add('D');
		}
		if(posx % 3 != 0 ) {
			list.add('L');
		}
		if(posx % 3 != 2 ) {
			list.add('R');
		}
		
		return list ;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
            return true;
        }
		
		if (!(o instanceof Puzzle) || o == null) {
            return false;
        }
		
		Puzzle p = (Puzzle) o ;
		return state == p.state ;
	}
	
	
	@Override 
	//Hashing function H4.
	public int hashCode() {
		int s = 0 ;
		char[] statestr = Integer.toString(state).toCharArray() ;
		for (int  i = 1 ; i < 9 ; i++ ) {
			s = s + (statestr[i] - 48)*i ;
		}
		return (state + s) % 362880 ;
	}
	
	@Override
	public String toString() {
		char[] statestr = Integer.toString(state).toCharArray() ;
		StringBuilder tostring = new StringBuilder() ;
		for (int i = 0 ; i < 9 ; i++) {
			if(statestr[i] == '9') tostring.append(" " + '\t') ;
			else tostring.append("" + statestr[i] + '\t') ;
			
			if(i % 3 == 2) tostring.append('\n');
		}
		return tostring.toString();
	}
	

}












