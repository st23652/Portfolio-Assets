import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class PuzzleSolver {
    // defining the goal state for the 8 puzzle problem
    // 0 represents the empty space
    static final int[][] GOAL_STATE = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 0}
    };

    // defining the initial state of the 8 puzzle problem
    static final int[][] INITIAL_STATE = {
            {8, 7, 6},
            {5, 4, 3},
            {2, 1, 0}
    };

    public static void main(String[] args) throws Exception {
        // a menu for the user to input to choose which search algorithm
        Scanner sc = new Scanner(System.in);
        System.out.println("Select search algorithm:\n1. Uniform Cost Search\n2. A* Search");
        int choice = sc.nextInt();
        sc.close();

        // determining the output file based on user choice
        String fileName;
        if (choice == 1) {
            fileName = "outputUniCost.txt";
        } else {
            fileName = "outputAstar.txt";
        }
        File outFile = new File(fileName);
        PrintWriter output = new PrintWriter(outFile);

        // tracker of execution time
        long start = System.currentTimeMillis();

        // solving the puzzle using the selected algorithm
        if (choice == 1) {
            // uniform cost search
            solver(output, false);
        } else {
            // a* search
            solver(output, true);
        }

        // the tracker being stopped
        long end = System.currentTimeMillis();
        output.println("Time taken: " + (end - start) + " ms");
        output.close();

        System.out.println("Results saved in " + fileName);
    }

    /**
     * Solves the 8-puzzle problem using Uniform Cost Search or A* Search.
     *
     * @param output   PrintWriter to write results to a file.
     * @param aStar Boolean flag to determine if A* Search should be used.
     */

    private static void solver(PrintWriter output, boolean aStar) {
       // over here priority queue to store nodes, sorted by cost function
        PriorityQueue<PuzzleNode> qu;
        if (aStar) {
            qu = new PriorityQueue<>(Comparator.comparingInt(PuzzleNode::getFCost));
        } else {
            qu = new PriorityQueue<>(Comparator.comparingInt(PuzzleNode::getGoalCost));
        }
        // setting to keep track of visited states
        Set<String> visited = new HashSet<>();

        // creating the beginning puzzle node with the starting state
        PuzzleNode beginning = new PuzzleNode(INITIAL_STATE, 0, heuristicCalculation(INITIAL_STATE), null);
        qu.add(beginning);
        visited.add(beginning.toString());

        // these are the tracking metrics
        int nodesExpanded = 0;
        int totalGeneratedNodes = 1;

        while (!qu.isEmpty()) {
            // getting the node with the lowest cost
            PuzzleNode current = qu.poll();
            nodesExpanded++;

            // if the goal state is reached then the solution and exit is returned
            if (current.isGoal()) {
                printSolution(output, current, nodesExpanded, totalGeneratedNodes - nodesExpanded);
                return;
            }

            // generating successors which means possible next moves
            for (PuzzleState nextState : current.getSuccessors()) {
                PuzzleNode nextNode = new PuzzleNode(nextState.board, current.goalCost + 1, heuristicCalculation(nextState.board), current);

                // if this state has not been visited yet, adding it to the queue
                if (!visited.contains(nextNode.toString())) {
                    qu.add(nextNode);
                    visited.add(nextNode.toString());
                    totalGeneratedNodes++;
                }
            }
        }
    }

    /**
     * Computes the heuristic value using Manhattan distance.
     * The Manhattan distance is the sum of the absolute differences
     * of the tiles' current positions from their goal positions.
     *
     * @param board The current state of the puzzle.
     * @return The heuristic cost.
     */
    private static int heuristicCalculation(int[][] board) {
        int manhattanDistance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // over here the empty tile is ignored
                if (board[i][j] != 0) {
                    // the expected row position
                    int targetX = (board[i][j] - 1) / 3;
                    // the expeected column position
                    int targetY = (board[i][j] - 1) % 3;
                    manhattanDistance += Math.abs(i - targetX) + Math.abs(j - targetY);
                }
            }
        }
        return manhattanDistance;
    }

    /**
     * Prints the solution path and statistics to the output file.
     *
     * @param output          PrintWriter to write the solution path.
     * @param state           The final solved puzzle node.
     * @param nodesExpanded   Number of nodes expanded during search.
     * @param nodesUnexpanded Number of nodes generated but not expanded.
     */
    private static void printSolution(PrintWriter output, PuzzleNode state, int nodesExpanded, int nodesUnexpanded) {
        Stack<PuzzleNode> path = new Stack<>();
        // traversing back from goal state to initial state using the parent nodes(PuzzleNode)
        while (state != null) {
            path.push(state);
            state = state.parent;
        }

        int moveCount = 0;

        // returning the sequence of the moves from the start to goal state
        while (!path.isEmpty()) {
            PuzzleNode s = path.pop();
            for (int[] row : s.board) {
                for (int num : row) {
                    output.print(num + " ");
                }
            }
            output.println();
            moveCount++;
        }

        // returning the statistics
        // excluding the initial state from move count
        output.println("Moves: " + (moveCount - 1));
        output.println("Nodes expanded: " + nodesExpanded);
        output.println("Nodes unexpanded: " + nodesUnexpanded);
    }
}