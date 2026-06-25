//You are supposed to add your comments

import java.util.*;

class AIplayer {
    // this enables us to store all points on the board
    List<Point> availablePoints;
    // and this allows to store scores related to each possible move
    List<PointsAndScores> rootsChildrenScores;
    // board object to represent the game board
    Board b = new Board();

    public AIplayer() {
    }

    // this method will return the best move by calculating the scores
    public Point returnBestMove() {
        // set to a very low value
        int MAX = -100000;
        // indexing to a best move
        int best = -1;

        // iterating through all possible moves and their scores
        for (int i = 0; i < rootsChildrenScores.size(); ++i) {
            if (MAX < rootsChildrenScores.get(i).score) {
                // only when the score is higher, the MAX is updated
                MAX = rootsChildrenScores.get(i).score;
                // updating the index with the best move
                best = i;
            }
        }
        // the point with higher score is returned
        return rootsChildrenScores.get(best).point;
    }

    // the method returns minimum values from a list of integers
    public int returnMin(List<Integer> list) {
        // with the highest possible value the MIN is initialized
        int min = Integer.MAX_VALUE;
        // index for the minimum value
        int index = -1;

        // iterating through the list to find minimum  value
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) < min) {
                // if smaller value is found, the MIN is updated
                min = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    // method to return the max value from the list of integers
    public int returnMax(List<Integer> list) {
        // the lowest possible value initializes max
        int max = Integer.MIN_VALUE;
        int index = -1;

        // iterating through the list, finding the max value
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i) > max) {
                // only when the larger value is found, the max value is updated
                max = list.get(i);
                index = i;
            }
        }
        return list.get(index);
    }

    // method to initiate the minimax algorithm with alpha beta pruning
    public void callMinimax(int depth, int turn, Board b, int alpha, int beta) {
        rootsChildrenScores = new ArrayList<>();
        // calling the minimax method
        minimax(depth, turn, b, alpha, beta);
    }

    // recursive minimax algorithm with alpha beta pruning to determine the best possible move
    public int minimax(int depth, int turn, Board b, int alpha, int beta) {
        // this line checks if X won so it can return 1 if true
        if (b.hasXWon()) return 1;
        // this line checks if O won so it can return -1 if true
        if (b.hasOWon()) return -1;

        // getting all possible points on the board
        List<Point> pointsAvailable = b.getAvailablePoints();
        // this line returns 0 to draw if no move is possible
        if (pointsAvailable.isEmpty()) return 0;

        // this list stores points from every possible move
        List<Integer> scores = new ArrayList<>();

        // iterating through every available points
        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);

            // this happens if its player with X turn
            if (turn == 1) {
                // marking X on the board
                b.placeAMove(point, 1);
                // recursively calling minimax for the O player turn
                int currentScore = minimax(depth + 1, 2, b, alpha, beta);
                // adding points to the list
                scores.add(currentScore);
                // updating alpha points with the max value
                alpha = Math.max(alpha, currentScore);

                // if at the root level, add the score and point to rootsChildrenScores
                if (depth == 0)
                    rootsChildrenScores.add(new PointsAndScores(currentScore, point));
                // this part only happens if it is the player with O turn
            } else if (turn == 2) {
                // placing O on the board
                b.placeAMove(point, 2);
                // recursively calling minimax for the X player turn
                int currentScore = minimax(depth + 1, 1, b, alpha, beta);
                // adding points to the list
                scores.add(currentScore);
                // updating beta points with the min value
                beta = Math.min(beta, currentScore);
            }
            // over here the move is undone to explore the other possibilities
            b.placeAMove(point, 0); // Undo move

            // alpha beta pruning exploration is stopped if alpha is greater than or equal to beta
            if (alpha >= beta) break;
        }
        // max points are returned if it is X player turn or else min points are returned
        return turn == 1 ? returnMax(scores) : returnMin(scores);
    }
}
