// representing a node in the search tree
// used for solving the puzzle
public class PuzzleNode extends PuzzleState {
    // goalCost is representing the cost from the start node to this node
    int goalCost;
    // heuristicCost is representing the heuristic cost which is the estimated cost to the goal
    int heuristicCost;
    // referencing to the parent node in PuzzleNode, useful for reconstructing the solution path
    PuzzleNode parent;

    // constructing to initialize the puzzle node with its board state, costs adn parent node
    public PuzzleNode(int[][] board, int goalCost, int heuristicCost, PuzzleNode parent) {
        // calling PuzzleState constructor to store board configuration
        super(board);
        this.goalCost = goalCost;
        this.heuristicCost = heuristicCost;
        this.parent = parent;
    }

    // the total cost function is computed and returned
    public int getFCost() {
        return goalCost + heuristicCost;
    }

    public int getGoalCost() {
        return goalCost;
    }
}
