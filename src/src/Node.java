package src;

import java.util.LinkedList;

public class Node extends Puzzle{
	
	private String path ;
	
	public Node() {
		super() ;
		path = "" ;
	}
	
	public Node(int state, String path) {
		super(state) ;
		this.path = path ;
	}
	
	public Node(Puzzle p, String path) {
		super(p.getState()) ;
		this.path = path ;
	} 
	
	public String getPath() {
		return path;
	}
	
	//returns all possible child nodes. 
	public LinkedList<Node> getChildNodes() {
		LinkedList<Node> childnodes = new LinkedList<Node>() ;
		Puzzle[] allmoves = new Puzzle[] {moveUp(), moveDown(), moveLeft(), moveRight()} ;
		char[] allpaths = {'U', 'D', 'L', 'R'} ;
		for(int i = 0 ; i < 4 ; i++) {
			if(allmoves[i] != null)	childnodes.add(new Node(allmoves[i], getPath() + allpaths[i])) ;
		}
		return childnodes ;
	}
	
	public int nodeDepth() {
		return getPath().length() ;
	}
	
	//heuristic function 1.
	public int heurManhattan() {
		return manhattan() + nodeDepth() ;
	}
	
	//heuristic function 2.
	public int heurMisplaced() {
		return misplaced() + nodeDepth() ;
	}
	
	//heuristic function 3.
	public int heurManhattan_RevPenalty() {
		return getReversalCount() + manhattan() + nodeDepth() ;
	}
	
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
            return true;
        }
		
		if (!(o instanceof Node) || o == null) {
            return false;
        }
		
		Node n = (Node) o ;
		return (this.getState() == n.getState() && nodeDepth() == n.nodeDepth() ) ;
	}
	
	

	
}
