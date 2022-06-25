package puzzle8;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author ABEDSSAMED
 */
public class BFS {

    private TaquinNode initialNode;
    Info info = new Info(); // a class that has the visited HashMap, Queue, time and space
    PathActions p = null;
    String Pathstr = "";

    public BFS(TaquinNode node) {
        this.initialNode = node;
    }

    public boolean search() {

        TaquinNode node = initialNode;
        this.info.queue.add(node);  //start Node is added to queue

        while (!(this.info.queue.isEmpty())) {   //loop keeps going as long as queue is not empty

            node = this.info.queue.remove();   //remove or pop the node and begin timer
            this.info.incTime();
           
            this.info.visited.put(node.hashCode(), node);   //places node in visited hashMap

            if (node.isGaol()) {  //if goal is found, a path is created and a path is printed
                this.p = new PathActions(initialNode, node, this.info);
                this.Pathstr = this.p.printPath();
                return true;
            }
            Successor s = new Successor();  //successor function that provides node's children
            List<TaquinNode> list = s.successor(node);
            for (TaquinNode temp : list) {
                boolean ans = this.info.visited.containsKey(temp.hashCode());
                if (ans == false) {
                    if (!(this.info.queue.contains(temp))) {
                        this.info.queue.add(temp);
                        this.info.queueSize();
                    }
                }
            }

        }

        return false;
    }

}
