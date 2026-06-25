import java.util.*;

public class PuzzleState {
    // the 3x3 array representing the puzzle board
    int[][] board;
    // this states the position of the empty tile [0]
    int emptyRow, emptyColumn;

    /**
     * Constructor to initialize the puzzle state.
     * It copies the provided board to prevent external modifications.
     *
     * @param board The initial puzzle board configuration.
     */
    public PuzzleState(int[][] board) {
        // initiating the new 3x3 board
        this.board = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, this.board[i], 0, 3);
        }
        // locating the empty tile [0] in the board
        findEmptyTile();
    }

    private void findEmptyTile() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // checking if the empty tile is found
                if (board[i][j] == 0) {
                    emptyRow = i;
                    emptyColumn = j;
                    return;
                }
            }
        }
    }

    /**
     * Checks if the current state matches the goal state.
     *
     * @return True if the board matches the goal state, otherwise false.
     */
    public boolean isGoal() {
        return Arrays.deepEquals(board, PuzzleSolver.GOAL_STATE);
    }

    /**
     * Generates all possible successor states by moving the empty tile in valid directions.
     *
     * @return A list of valid PuzzleState objects representing next possible moves.
     */
    public List<PuzzleState> getSuccessors() {
        List<PuzzleState> successors = new ArrayList<>();
        // up, down, left, right
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        for (int[] move : moves) {
            int newRow = emptyRow + move[0];
            int newColumn = emptyColumn + move[1];

            // ensuring if the move is within the boards boundaries
            if (newRow >= 0 && newRow < 3 && newColumn >= 0 && newColumn < 3) {
                // creating a new board state by moving the empty tile with the adjacent tile
                int[][] newBoard = new int[3][3];
                for (int i = 0; i < 3; i++) {
                    System.arraycopy(board[i], 0, newBoard[i], 0, 3);
                }
                // moving the empty tile with the tile at a new position
                newBoard[emptyRow][emptyColumn] = newBoard[newRow][newColumn];
                newBoard[newRow][newColumn] = 0;

                // adding the new state to the successor list
                successors.add(new PuzzleState(newBoard));
            }
        }
        return successors;
    }

    /**
     * Returns a string representation of the puzzle board for easy comparison and debugging.
     *
     * @return A string representation of the board.
     */
    public String toString() {
        return Arrays.deepToString(board);
    }
}
