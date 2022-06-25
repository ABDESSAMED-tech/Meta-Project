package puzzle8;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ABEDSSAMED
 */
public class Successor {

    public Successor() {

    }
    private int state;

    public Successor(int state) {
        this.state = state;

    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
		this.state = state;
	}
    public List<TaquinNode> successor(TaquinNode node) {
        //fonction successeur qui prend un état et renvoie une liste d'états possibles pouvant être atteints

        List<TaquinNode> list = new ArrayList<TaquinNode>();

        int row = node.getRowBlank();
        int col = node.getColBlank();

        //up
        if ((col != 0 || col != 1 || col != 2) && (row != 0)) {  //utilise des informations sur la nature des tableaux 2d pour dicter le mouvement de la tuile zéro.
            TaquinNode upNode = node.createChild(row - 1, col);
            upNode.setDir(DIRECTIONS.UP);
            list.add(upNode);
            

        }

        //down
        if ((col != 0 || col != 1 || col != 2) && (row != 2)) {
            TaquinNode downNode = node.createChild(row + 1, col);
            downNode.setDir(DIRECTIONS.DOWN);
            list.add(downNode);
            
        }

        //right
        if ((row != 0 || row != 1 || row != 2) && (col != 2)) {
            TaquinNode rightNode = node.createChild(row, col + 1);
            rightNode.setDir(DIRECTIONS.RIGHT);
            list.add(rightNode);
        }

        //left
        if ((row != 0 || row != 1 || row != 2) && (col != 0)) {
            TaquinNode leftNode = node.createChild(row, col - 1); // a child is created if the zero tile can move left.
            leftNode.setDir(DIRECTIONS.LEFT);
            list.add(leftNode);
        }
        for(int i=0;i<list.size();i++){
            list.get(i).getMatrix();
        }
        return list;  //une liste des fils est renvoyée.

    }
}
