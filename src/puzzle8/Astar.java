package puzzle8;

import java.util.Comparator;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 *
 * @author ABEDSSAMED
 */

public class Astar {
    //Astar class that creates the Astar search

    private TaquinNode initialNode;
    private int i;
    Info info = new Info(); // a class that has the visited HashMap, Queue, time and space
    PathActions p = null;
    String Pathstr = "";

    public Astar(TaquinNode node, int i) {
        this.initialNode = node;
        this.i = i; // this int value helps determine which heuristic will be used
    }

    private class f1Comparator implements Comparator<TaquinNode> {  //comparator for tiles misplaced heuristic that will be used in Priority Queue

        Heuristics h = new Heuristics();

        public int compare(TaquinNode a, TaquinNode b) {
            return (a.getMaxCost() + h.numCorPos(a)) - (b.getMaxCost() + h.numCorPos(b));
        }
    }

    private class f2Comparator implements Comparator<TaquinNode> {			//comparator for manhattan heuristic and totalCost 

        Heuristics h = new Heuristics();

        public int compare(TaquinNode a, TaquinNode b) {
            return (a.getMaxCost() + h.manhattan(a)) - (b.getMaxCost() + h.manhattan(b));
        }
    }

    public boolean search() {

        //Astar search which creates a priority queue which sorts according to h(n)
        if (this.i == 1) {
            this.info.makePQueue(new f1Comparator());
        } else {
            this.info.makePQueue(new f2Comparator());
        }
        //making a priority queue with one of the heuristics determine the Comparator
        TaquinNode node = initialNode;
        this.info.pQueue.add(node);

        while (!(this.info.pQueue.isEmpty())) {
            node = this.info.pQueue.poll();
            this.info.incTime();
         
            this.info.visited.put(node.hashCode(), node);
            if (node.isGaol()) {
                p = new PathActions(initialNode, node, this.info); // class that creates a path from goal to start Node if goal is reached.
                this.Pathstr = this.p.printPath(); // the path is then printed
                return true;
            }

            Successor s = new Successor(); // Successor class created to provide next possible moves from current node
            List<TaquinNode> list = s.successor(node); // list of potential children

            for (TaquinNode temp : list) {
                boolean ans = this.info.visited.containsKey(temp.hashCode()); //Uses temporary node's hashCode to check if it has been expanded or not.
                if (ans == false) { //if it hasn't been expanded then we can now check if there is a node in the Priority Queue with a higher Cost
                    if (!(this.info.pQueue.contains(temp))) {
                        this.info.pQueue.add(temp);
                        this.info.pQueueSize();
                    }

                }
            }
        }
        return false;
    }

}
