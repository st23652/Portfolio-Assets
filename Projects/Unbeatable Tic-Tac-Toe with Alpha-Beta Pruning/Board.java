// You are supposed to add your comments

import java.util.*;

// with the x & y coordinates, the board is represented with points
class Point {
    int x, y;

    // constructor to initialize with these coordinates
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // to display the points in a readable format, an override of the toString method is done here
    @Override
    // adding 1 to make it user-friendly, it makes it 1 based index
    public String toString() {
        return "[" + (x+1) + ", " + (y+1) + "]";
    }
}

// this is a class to store a point along with its associated score
class PointsAndScores {
    int score;
    Point point;

    // this is a constructor to initialize the associated score and point
    PointsAndScores(int score, Point point) {
        this.score = score;
        this.point = point;
    }
}

// this is a cass to represent the game board and its operations
class Board {
    List<Point> availablePoints;
    // this part scans for user input
    Scanner scan = new Scanner(System.in);
    // display of 3x3 board in a 2D array
    int[][] board = new int[3][3];

    // this is a default constructor
    public Board() {
    }

    // this part checks if the game is over or not
    public boolean isGameOver() {
        return (hasXWon() || hasOWon() || getAvailablePoints().isEmpty());
    }

    // over here checking if player x won
    public boolean hasXWon() {
        // checking for diagonal wins
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {
            return true;
        }
        // checking for row and column winnings
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1))) {
                return true;
            }
        }
        return false;
    }

    // checking if player o has won or not
    public boolean hasOWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {
             return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {
                return true;
            }
        }
        return false;
    }

    // list of points available is returned where a move can be made
    public List<Point> getAvailablePoints() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                // 0 over here represents an empty spot
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    // over here state of a specific board position is returned
    public int getState(Point point){
    	return board[point.x][point.y];
    }

    // for the given player, the a move on the board is placed
    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;   
    }

    // in a user-friendly way, the current board state is displayed
    public void displayBoard() {
        System.out.println();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
 		if (board[i][j]==1)           
                    System.out.print("X ");
                else if (board[i][j]==2)
                    System.out.print("O ");
                else
                    // the way empty spaces is represented
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}
