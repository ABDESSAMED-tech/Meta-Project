package puzzle8;

import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author ABEDSSAMED
 */
public class DFS {

    private TaquinNode initialNode;
    Info info = new Info();
    PathActions p = null;
    String Pathstr = "";
    //DFS constructor that creates the DFS object

    public DFS(TaquinNode node) {
        this.initialNode = node;
    }

    public boolean search() {

        TaquinNode node = initialNode;
        this.info.stack.push(node);  // a stack is used to ensure LIFO

        while (!(this.info.stack.isEmpty())) {

            node = this.info.stack.pop();
            this.info.incTime();
            
            this.info.visited.put(node.hashCode(), node);

            if (node.isGaol()) {
                p = new PathActions(initialNode, node, this.info);
                this.Pathstr = p.printPath();
                return true;
            }
            Successor s = new Successor();
            List<TaquinNode> list = s.successor(node);
            for (TaquinNode temp : list) {
                boolean ans = this.info.visited.containsKey(temp.hashCode());
                if (ans == false) {
                    if (!(this.info.stack.contains(temp))) { //checking the stack for duplicate children
                        this.info.stack.push(temp);
                        this.info.stackSize();
                    }

                }
            }

        }

        return false;
    }
}
