package puzzle8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author ABEDSSAMED
 */
public class PathActions {
    // this class provides an object that is used to trace back the path from the goal
    // it then get the path 

    List<TaquinNode> path;
    int depth = 1;
    Info info; //info object is used in order to print details about space and time

    public PathActions(TaquinNode initialNode, TaquinNode goalNode, Info inf) {  //the arguments are goalNode, info and intialNode so a path can be found.
        path = this.getPath(initialNode, goalNode);
        this.info = inf;
    }

    private List<TaquinNode> getPath(TaquinNode initialNode, TaquinNode goalNode) {  //given a goalNode and initialNode this method uses node's parents to trace it's way back up
        TaquinNode tempNode = goalNode;
        List<TaquinNode> list = new ArrayList<TaquinNode>();

        while (!(tempNode.equals(initialNode))) {
            list.add(tempNode);
            tempNode = tempNode.getParent();

        }
        list.add(initialNode);
        return list;  // a list of the path is returned in reverse order
    }

    public String printPath() {   //this method enables us to print the path in correct order from start node to goal node with sufficient details. 
        int size = path.size();
        
        String paths = "";
        int k = 0;
        String p = "";
        for (int i = size - 2; i >= 0; i--) {
            k++;
            p = path.get(i).getDir().toString();
            
            // Traduction *-*
            if(p == "UP"){
                p="Haut";
            }else 
                if(p == "DOWN"){
                    p="bas";
                }
                else if(p == "LEFT"){
                    p= "Gauche";
                }
                else if(p == "RIGHT"){
                   p= "Droit";
                }
            
            paths = paths + "\n" + k + "-Déplacer le blanc  vers : "+p; // add  pathe
            depth = path.get(i).getDepth();
      }
      return paths;

    }
}
