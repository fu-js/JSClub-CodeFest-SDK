package jsclub.codefest.sdk.socket.data;

import java.util.ArrayList;
import java.util.List;

public class Position {
    public int col;
    public int row;

    public int getX() {
        return col;
    }

    public int getY() {
        return row;
    }

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public List<Node> getAllPosition(Position currentP, int direction){
        List<Node> allNode = new ArrayList<>();
        if(direction!=0){
            //currentP
            Node currentN = new Node(currentP.getX(), currentP.getY());
            allNode.add(currentN);
            //futureP using system direction
            allNode.add(currentN.nextPosition(direction,1));
            //all futureP after
            Node futureN = new Node(currentN.nextPosition(direction,1).getX(),currentN.nextPosition(direction,1).getY());
            allNode.add(futureN.nextPosition(1, 1));
            allNode.add(futureN.nextPosition(2, 1));
            allNode.add(futureN.nextPosition(3, 1));
            allNode.add(futureN.nextPosition(4, 1));
        }
        return allNode;
    }
}
