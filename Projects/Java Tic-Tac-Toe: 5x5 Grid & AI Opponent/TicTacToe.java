import java.util.*;
import java.util.concurrent.*;

public class TicTacToe {

    /**
     * This class represents a 5x5 TicTacToe game where the player competes against an AI.
     * The game has two difficulty levels: easy and medium.
     * The player needs to get 4 marks in a row (horizontally, vertically, or diagonally) to win.
     */

    private static final int SIZE = 5;

    // the number of consecutive marks that are needed to win
    private static final int WIN_CONDITION = 4;

    // empty cell
    private static final char EMPTY = '-';

    // player
    private static final char PLAYER = 'X';

    // ai
    private static final char AI = 'O';

    // the 2d array representing
    private static char[][] board = new char[SIZE][SIZE];

    // this is the variable to store the difficulty which are easy and medium
    private static String lvl;

    // these variables track the scores for the player and ai
    private static int playerPoints = 0;
    private static int aiPoints = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String nxtGame;

        // this loop is to make sure that the game can be repeated
        do {

            // resetting the board before the start of each game
            initialize();
            System.out.println("Select level: easy, medium");

            // user selecting the level they want to play
            lvl = sc.nextLine().trim().toLowerCase();

            // printing the initial board
            print();
            while (true) {

                // the move of the player
                playerMove(sc);

                // the board gets updated and prints
                print();

                // checking is the player wins
                if (winner(PLAYER)) {
                    System.out.println("Player wins!");

                    // adding score for the player
                    addPoints("Player");
                    break;
                }

                // over here the board is checked if the board is full causing a draw
                if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }

                aiMove();

                // again printing the updated board
                print();

                // checking if the ai won
                if (winner(AI)) {
                    System.out.println("AI wins!");

                    // adding scores for the ai
                    addPoints("AI");
                    break;
                }
                if (isBoardFull()) {
                    System.out.println("It's a draw!");
                    break;
                }
            }

            // to repeat the game, the player is asked if they want to play again
            System.out.println("Do you want to play again? (y/n)");
            nxtGame = sc.nextLine().trim().toLowerCase();

        } while (nxtGame.equals("y"));

        // printing the final scores
        System.out.println("Final Scores:");
        System.out.println("Player: " + playerPoints);
        System.out.println("AI: " + aiPoints);

        System.out.println("Thank you for playing!");

        // to prevent the resource leak, the scanner is closed
        sc.close();
    }

    // by the emptying all cells to initialize the game board
    private static void initialize() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = EMPTY;
    }

    // printing the current state of the board
    private static void print() {
        for (char[] r : board) {
            for (char cell : r) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    // validating the players move
    private static void playerMove(Scanner sc) {
        int r, c;
        while (true) {
            System.out.println("Enter row and column (1-based index):");

            // converting to a 0 based index
            r = sc.nextInt() - 1;
            c = sc.nextInt() - 1;

            if (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c] == EMPTY) {

                // placing the marks of the player on the board
                board[r][c] = PLAYER;
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        // consuming the remaining newline
        sc.nextLine();
    }

    // logical move of ai
    private static void aiMove() {
        System.out.println("AI is thinking...");

        // setting up an ExecutorService to run the ai move with a time limit
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // tracking is the move is made
        final boolean[] moveMade = {false};

        // holding the move coordinates
        final int[] move = new int[2];

        // submitting the move calculation task of the ai to the executor
        Future<Void> future = executor.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {

                // getting the best move for the ai
                int[] aiMove = getAIMove();
                move[0] = aiMove[0];
                move[1] = aiMove[1];
                moveMade[0] = true;

                // returning null because it is a void
                return null;
            }
        });

        try {

            // waiting for the move to complete within the 15 seconds time limit
            future.get(15, TimeUnit.SECONDS);
            if (moveMade[0]) {

                // placing ai marks
                board[move[0]][move[1]] = AI;
                System.out.println("AI chooses move: [" + (move[0] + 1) + ", " + (move[1] + 1) + "]");
            }
        } catch (TimeoutException e) {

            // if the ai takes more than 15 secs it will force a move
            System.out.println("AI took too long! Forcing a random move.");

            // generating a random move
            int[] randomMove = randomMove();
            board[randomMove[0]][randomMove[1]] = AI;
            System.out.println("AI forces move: [" + (randomMove[0] + 1) + ", " + (randomMove[1] + 1) + "]");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {

            // shutting down the executor service
            executor.shutdownNow();
        }
    }

    /**
     * Decides the AI's move based on the selected difficulty.
     *
     * @return The coordinates of the AI's next move.
     */
    private static int[] getAIMove() {
        int[] move = null;
        if ("easy".equals(lvl)) {

            // easy ai makes a random move
            move = randomMove();
        } else if ("medium".equals(lvl)) {

            // medium ai uses some strategy
            move = mediumMove();
        }
        return move;
    }

    /**
     * Makes a random move by the AI.
     *
     * @return The coordinates of a random valid move.
     */
    private static int[] randomMove() {
        Random rand = new Random();
        int r, c;
        do {
            r = rand.nextInt(SIZE);
            c = rand.nextInt(SIZE);
        } while (board[r][c] != EMPTY);
        return new int[]{r, c};
    }

    /**
     * Determines the AI's move by checking if it can win or block the player.
     *
     * @return The coordinates of the move that blocks or wins for the AI or player.
     */
    private static int[] mediumMove() {

        // checking if the ai can win
        int[] aiWinningMove = findWinningMove(AI);
        if (aiWinningMove != null) {

            // returning the winning move for ai
            return aiWinningMove;
        }

        // checking if the player can win and block it
        int[] playerWinningMove = findWinningMove(PLAYER);
        if (playerWinningMove != null) {

            // blocking the players winning move
            return playerWinningMove;
        }

        // if no winning or blocking move, picking the first available spot
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return new int[]{i, j};
                }
            }
        }

        // if no better option is found, a random move is made
        return randomMove();
    }

    /**
     * Checks for a potential winning move for a given player.
     *
     * @param player The player (AI or PLAYER) to check the winning move for.
     * @return The coordinates of the winning move, or null if no winning move is found.
     */
    private static int[] findWinningMove(char player) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = player;

                    // checking if the mark placed results in a win
                    if (winner(player)) {

                        // undoing the move
                        board[i][j] = EMPTY;
                        return new int[]{i, j};
                    }

                    // undoing the move
                    board[i][j] = EMPTY;
                }
            }
        }

        // no winning move is found
        return null;
    }


    /**
     * Checks if a player has won by having 4 marks in a row.
     *
     * @param player The player (AI or PLAYER) to check for a win.
     * @return True if the player has won, false otherwise.
     */
    private static boolean winner(char player) {
        return rows(player) || columns(player) || diagonals(player);
    }

    /**
     * Checks all rows for a winning condition (4 marks in a row).
     *
     * @param player The player to check for a win.
     * @return True if any row contains 4 marks in a row, false otherwise.
     */
    private static boolean rows(char player) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j <= SIZE - WIN_CONDITION; j++) {
                boolean win = true;
                for (int k = 0; k < WIN_CONDITION; k++) {
                    if (board[i][j + k] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }
        return false;
    }

    /**
     * Checks all columns for a winning condition (4 marks in a row).
     *
     * @param player The player to check for a win.
     * @return True if any column contains 4 marks in a row, false otherwise.
     */
    private static boolean columns(char player) {
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i <= SIZE - WIN_CONDITION; i++) {
                boolean win = true;
                for (int k = 0; k < WIN_CONDITION; k++) {
                    if (board[i + k][j] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }
        return false;
    }

    /**
     * Checks both diagonals for a winning condition (4 marks in a row).
     *
     * @param player The player to check for a win.
     * @return True if any diagonal contains 4 marks in a row, false otherwise.
     */
    private static boolean diagonals(char player) {
        for (int i = 0; i <= SIZE - WIN_CONDITION; i++) {
            for (int j = 0; j <= SIZE - WIN_CONDITION; j++) {
                boolean win = true;
                for (int k = 0; k < WIN_CONDITION; k++) {
                    if (board[i + k][j + k] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }
        for (int i = 0; i <= SIZE - WIN_CONDITION; i++) {
            for (int j = WIN_CONDITION - 1; j < SIZE; j++) {
                boolean win = true;
                for (int k = 0; k < WIN_CONDITION; k++) {
                    if (board[i + k][j - k] != player) {
                        win = false;
                        break;
                    }
                }
                if (win) return true;
            }
        }
        return false;
    }

    /**
     * Checks if the board is full, indicating a draw.
     *
     * @return True if the board is full, false otherwise.
     */
    private static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == EMPTY)
                    return false;
        return true;
    }

    /**
     * Adds a point to the player's score based on the result of the game.
     *
     * @param winner The winner of the game ("Player" or "AI").
     */
    private static void addPoints(String winner) {
        int points = 0;
        if ("easy".equals(lvl)) {
            points = 50;
        } else if ("medium".equals(lvl)) {
            points = 100;
        }

        if ("Player".equals(winner)) {
            playerPoints += points;
        } else if ("AI".equals(winner)) {
            aiPoints += points;
        }

        System.out.println(winner + " gets " + points + " points!");
    }
}
