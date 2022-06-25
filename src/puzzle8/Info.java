package puzzle8;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author ABEDSSAMED
 */
public class Info {
    //info class that is used in every search 

    public Queue<TaquinNode> queue; // this class has datastructures such as queue, stack, and priority Queue in order to keep track of time, space and which nodes are in the queue 
    public Stack<TaquinNode> stack;
    public PriorityQueue<TaquinNode> pQueue;
    public int time;
    private int maxQueueSize;
    public HashMap<Integer, TaquinNode> visited;

    public Info() {
        queue = new LinkedList<TaquinNode>();
        stack = new Stack<TaquinNode>();
        pQueue = new PriorityQueue<TaquinNode>();
        time = 0;
        maxQueueSize = 0;
        visited = new HashMap<Integer, TaquinNode>();

    }

    public void makePQueue(Comparator c) {   //creates a prioirty queue with a comparator as an argument to decidee the order in which the queue will organize elements
        pQueue = new PriorityQueue<TaquinNode>(c);
    }

    public void incTime() {  //timer method that begins timer ,number of node generated
        time += 1;
    }

    public void queueSize() {   //queue size that constantly checks for maximum size, this will tell us the space
        if (queue.size() > maxQueueSize) {
            maxQueueSize = queue.size();
        }
      
    }

    public void stackSize() {   //behaves similar to queueSize() but for stack
        if (stack.size() > maxQueueSize) {
            maxQueueSize = stack.size();
        }
       

    }

    public void pQueueSize() {  //behaves similar to queueSize() but for priority queue
        if (pQueue.size() > maxQueueSize) {
            maxQueueSize = pQueue.size();
        }
        

    }

    public int getTime() { //number of nodes is returned
        return time;
    }

    public int getSpace() {  //space is returned
        return maxQueueSize;
    }

}
