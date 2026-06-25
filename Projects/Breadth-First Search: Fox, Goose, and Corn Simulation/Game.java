import java.util.ArrayList;

public class Game {
    // Current state of the Game represented as a char array
    // Index 0 = Fox, Index 1 = Goose, Index 2 = Corn, Index 3 = Farmer
    final char[] board;
    static final int BOARD_SIZE = 4;

    // Initial and Goal states
    static final char[] INITIAL_BOARD = {'L', 'L', 'L', 'L'};
    static final char[] GOAL_BOARD = {'R', 'R', 'R', 'R'};

    // Constructor
    public Game(char[] board) {
        this.board = board.clone();
    }

    // Clones the current state
    public Game clone() {
        return new Game(this.board.clone());
    }

    // Converts board to string representation
    public String toString() {
        return "[" + new String(board) + "]";
    }

    // Checks if the current state is the goal state
    public boolean isGoal() {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (this.board[j] != GOAL_BOARD[j]) return false;
        }
        return true;
    }

    // Compares two board states
    public boolean sameBoard(Game gs) {
        for (int j = 0; j < BOARD_SIZE; j++) {
            if (this.board[j] != gs.board[j]) return false;
        }
        return true;
    }

    // Returns all possible valid moves from the current state
    public ArrayList<Game> possibleMoves() {
        ArrayList<Game> moves = new ArrayList<>();

        char farmer = board[3];

        // Move Farmer alone
        Game newState = this.clone();
        newState.board[3] = (farmer == 'L') ? 'R' : 'L';
        if (isValidatedState(newState)) {
            moves.add(newState);
        }

        // Move Farmer with one entity (Fox, Goose, or Corn)
        for (int i = 0; i < BOARD_SIZE - 1; i++) {
            if (board[i] == farmer) { // Only move entities that are with the farmer
                Game newStateWithEntity = this.clone();
                newStateWithEntity.board[i] = (farmer == 'L') ? 'R' : 'L';
                newStateWithEntity.board[3] = newStateWithEntity.board[i]; // Move farmer too

                if (isValidatedState(newStateWithEntity)) {
                    moves.add(newStateWithEntity);
                }
            }
        }
        return moves;
    }

    // Validates whether the state is safe
    private boolean isValidatedState(Game state) {
        char farmer = state.board[3];
        char fox = state.board[0];
        char goose = state.board[1];
        char corn = state.board[2];

        // If Farmer is not present, Goose and Fox together is unsafe
        if (fox == goose && fox != farmer) return false;

        // If Farmer is not present, Goose and Corn together is unsafe
        if (goose == corn && goose != farmer) return false;

        return true;
    }

    public static void main(String[] args) {
        Game game = new Game(INITIAL_BOARD);
        System.out.println("Initial State: " + game);

        ArrayList<Game> moves = game.possibleMoves();
        System.out.println("Possible Moves:");
        for (Game move : moves) {
            System.out.println(move);
        }
    }
}
