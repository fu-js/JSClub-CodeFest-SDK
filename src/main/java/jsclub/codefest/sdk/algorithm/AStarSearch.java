package jsclub.codefest.sdk.algorithm;

import jsclub.codefest.sdk.constant.MapEncode;
import jsclub.codefest.sdk.socket.data.Node;
import jsclub.codefest.sdk.socket.data.Position;
import java.util.*;

public class AStarSearch extends BaseAlgorithm {
    /**
     * > Given a matrix, a list of restricted nodes, a start node and an end node,
     * return a stack of nodes
     * that represents the shortest path from the start node to the end node
     * 
     * @param matrix       the matrix of the maze
     * @param restrictNode a list of positions that are not allowed to be visited.
     * @param start        the start position
     * @param end          the end position of the path
     * @return A string of the steps taken to get from the start to the end.
     */
    public static String aStarSearch(int[][] matrix, List<Position> restrictNode, Position start, Position end) {
        Node startNode = Node.createFromPosition(start);
        Node endNode = Node.createFromPosition(end);

        List<Node> restrictNodeList = new ArrayList<Node>();
        for (Position position : restrictNode) {
            restrictNodeList.add(Node.createFromPosition(position));
        }

        Stack<Node> steps = aStarSearch(matrix, restrictNodeList, startNode, endNode);
        return getStepsInString(startNode, steps);
    }

    /**
     * The open list is the Node that needs to be checked, and the closed list is
     * the Node that has been
     * checked. The open list is empty, indicating that there is no new Node to add,
     * and there is no end
     * Node in the tested Node, the path can not be found
     * 
     * @param matrix       The map matrix, which is a two-dimensional array of
     *                     integers.
     * @param restrictNode The Node that can't be passed, such as the wall, the box,
     *                     the player, etc.
     * @param start        The starting point of the pathfinding
     * @param target       The target Node
     * @return A stack of nodes.
     */
    private static Stack<Node> aStarSearch(int[][] matrix, List<Node> restrictNode, Node start, Node target) {
        int mMapWidth = matrix.length;
        int mMapHeight = matrix[0].length;

        ArrayList<Node> openList = new ArrayList<>();
        ArrayList<Node> closeList = new ArrayList<>();
        Stack<Node> stack = new Stack<>();// Elephant to eat the path
        openList.add(Node.createFromPosition(start));// Place the start Node in the open list;
        start.setH(manhattanDistance(start, target));

        while (!openList.isEmpty()) {
            Node now = null;
            int minValue = Integer.MAX_VALUE;
            for (Node n : openList) {// We find the F value (the description farthest from the target), if the same
                // we choose behind the list is the latest addition.
                if (n.getF() < minValue) {
                    minValue = n.getF();
                    now = n;
                }
                if (now != null && n.getF() == minValue
                        && (distanceBetweenTwoPoints(n, start) < distanceBetweenTwoPoints(now, start))) {
                    now = n;
                }

            }
            // Remove the current Node from the open list and add it to the closed list
            openList.remove(now);
            closeList.add(now);
            // Neighbor in four directions
            Node left = Node.createFromPosition(now.leftPosition(1));
            Node right = Node.createFromPosition(now.rightPosition(1));
            Node up = Node.createFromPosition(now.upPosition(1));
            Node down = Node.createFromPosition(now.downPosition(1));
            List<Node> temp = new ArrayList<>(4);
            temp.add(up);
            temp.add(right);
            temp.add(down);
            temp.add(left);
            for (Node n : temp) {
                // If the neighboring Node is not accessible or the neighboring Node is already
                // in the closed list, then no action is taken and the next Node continues to be
                // examined;
                if ((!n.equals(target) && !isValidNode(matrix, n, restrictNode))
                        || closeList.contains(n)
                        || n.getX() > mMapWidth
                        || n.getX() < 1
                        || n.getY() > mMapHeight
                        || n.getY() < 1) {
                    continue;
                }

                // If the neighbor is not in the open list, add the Node to the open list,
                // and the adjacent Node'elephant father Node as the current Node, while saving
                // the
                // adjacent Node G and H value, F value calculation I wrote directly in the Node
                // class
                if (!openList.contains(n)) {
                    // Logger.println("ok");
                    n.setFather(now);
                    n.setG(now.getG() + 1);
                    n.setH(manhattanDistance(n, target));
                    openList.add(n);
                    // When the destination Node is added to the open list as the Node to be
                    // checked, the path is found, and the loop is terminated and the direction is
                    // returned.
                    if (n.equals(target)) {
                        // Go forward from the target Node, .... lying groove there is a pit, Node can
                        // not use f, because f and find the same Node coordinates but f did not record
                        // father
                        Node node = openList.get(openList.size() - 1);
                        while (node != null
                        // && !node.equals(playerNode)???????
                        ) {
                            stack.push(node);
                            node = node.getFather();
                        }
                        // Create previous step to finding out next step

                        return stack;
                    }
                }
                // If the neighbor is in the open list,
                // // judge whether the value of G that reaches the neighboring Node via the
                // current Node is greater than or less than the value of G that is stored
                // earlier than the current Node (if the value of G is greater than or smaller
                // than the value of G), set the father Node of the adjacent Node as Current
                // Node, and reset the G and F values ​​of the adjacent Node.
                if (openList.contains(n)) {
                    if (n.getG() > (now.getG() + 1)) {
                        n.setFather(now);
                        n.setG(now.getG() + 1);
                    }
                }
            }
        }
        // When the open list is empty, indicating that there is no new Node to add, and
        // there is no end Node in the tested Node, the path can not be found. At this
        // moment, the loop returns -1 too.
        return new Stack<>();
    }

    private static Boolean isValidNode(int[][] matrix, Node n, List<Node> restrictNode) {
        if (n.getX() >= matrix.length || n.getX() < 0 || n.getY() >= matrix[0].length || n.getY() < 0) {
            return false;
        }

        if (matrix[n.getX()][n.getY()] == MapEncode.WALL) {
            return false;
        }
        return !restrictNode.contains(n);
    }

    /**
     * It takes in a matrix, a list of restricted nodes, a start position, and a list of target positions,
     * and returns a map of target positions to the path to that target
     * 
     * @param matrix the matrix of the map
     * @param restrictNode a list of positions that the algorithm cannot go through.
     * @param start The starting position of the agent.
     * @param targets a list of target positions
     * @return A map of positions and strings.
     */
    public static Map<Position, String> getPathToAllTargets(int[][] matrix, List<Position> restrictNode, Position start,
            ArrayList<Position> targets) {
        Map<Position, String> result = new HashMap<>();
        for (Position target : targets) {
            String path = aStarSearch(matrix, restrictNode, start, target);

            if (!path.isEmpty()) {
                result.put(target, path);
            }
        }

        return result;
    }
}
