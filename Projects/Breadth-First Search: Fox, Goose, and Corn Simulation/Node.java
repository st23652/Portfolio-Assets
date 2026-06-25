import java.util.ArrayList;

/*
	The class Node represents nodes.
	Its essential features are a GameState and a reference to the node's parent node.
	The latter is used to assemble and output the solution path once the goal sate has been reached.
 */

public class Node {
    // The state associated with the node
    Game state;
    // The node from which this node was reached.
    Node parent;
    // The cost of reaching this node from the initial node, i.e., number of moves.
    private int cost;

    /*
      	Constructor used to create new nodes.
     */
    public Node(Game state, Node parent, int cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    /*
      	Constructor used to create initial node.
     */
    public Node(Game state) {
        this(state,null,0);
    }

    public int getCost() {
        return cost;
    }

    public String toString() {
        return "Node:" + state + " ";
    }

    /*
      	Given a list of nodes as first argument, findNodeWithState searches the list for a node
       	whose state is that specified as the second argument.
       	If such a node is in the list, the first one encountered is returned.
       	Otherwise null is returned.
     */
    public static Node findNodeWithState(ArrayList<Node> nodeList, Game gs) {
        for (Node n : nodeList) {
            if (gs.sameBoard(n.state)) return n;
        }
        return null;
    }
}
