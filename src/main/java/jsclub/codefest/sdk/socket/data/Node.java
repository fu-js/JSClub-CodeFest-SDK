package jsclub.codefest.sdk.socket.data;

public class Node extends Position{
    private int G =0;
    private int H =0;
    public Node father;
    public double V = 1;

    public Node(int x, int y) {
        super(x, y);
    }

    public int getF(){
        return G+H;
    }
    public int getG(){
        return G;
    }
    public void setG(int g){
        G = g;
    }
    public int getH(){
        return H;
    }
    public void setH(int h){
        H =h;
    }

    public void setV(double v) {
        V = v;
    }

    public double getV() {
        return V;
    }

    public Node getFather(){
        return father;
    }
    public void setFather(Node father){
        this.father = father;
    }
    public int getX(){
        return col;
    }
    public void setX(int x) {
        this.col = x;
    }
    public int getY() {
        return row;
    }
    public void setY(int y) {
        this.row = y;
    }

    public String toString(){
        return col +"-"+ row;
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj instanceof Node){
            Node antherNode = (Node) obj;
            return this.col == antherNode.col && this.row == antherNode.row;
        }
        return false;
    }

    public Node leftPosition(int step) {
        return new Node(col - step,row);
    }

    public Node rightPosition(int step) {
        return new Node(col + step,row);
    }

    public Node upPosition(int step) {
        return new Node(col,row - step);
    }

    public Node downPosition(int step) {
        return new Node(col,row + step);
    }

    public Node nextPosition(int direction,int step) {
        switch (direction) {
            case 1:
                return leftPosition(step);
            case 2:
                return rightPosition(step);
            case 3:
                return upPosition(step);
            case 4:
                return downPosition(step);
            default:
                return null;
        }
    }

    public static Node createFromPosition(Position position) {
        return new Node(position.col, position.row);
    }


}
