//You are supposed to add your comments

import java.util.*;

// main class for running the tic-tac-toe game
public class TicTacToe {

    public static void main(String[] args) {
        // creating ai player instance
        AIplayer AI= new AIplayer();
        // creating a new game board
        Board b = new Board();
        // initializing a point object
        Point p = new Point(0, 0);
        // random number generator
        Random rand = new Random();

        // displaying the initial empty board
        b.displayBoard();

        // asking the user who should play first
        System.out.println("Who makes first move? (1)Computer (2)User: ");
        int choice = b.scan.nextInt();

        // if the computer starts, execute ai move first
        if(choice == 1){
            // ai calculating the best move
            AI.callMinimax(0, 1, b, choice, choice);
	        // display ai's possible moves with scores
            for (PointsAndScores pas : AI.rootsChildrenScores) {
	            System.out.println("Point: " + pas.point + " Score: " + pas.score);
	        }
            // ai places the best move on the board
            b.placeAMove(AI.returnBestMove(), 1);
            // displaying updated board
            b.displayBoard();
        }

        // this is a main game loop where it keeps running until the game is over
        while (!b.isGameOver()) {
            // prompting the user for their move
            System.out.println("Your move: line (1, 2, or 3) colunm (1, 2, or 3)");
            // converting to the zero based index
            Point userMove = new Point(b.scan.nextInt()-1, b.scan.nextInt()-1);

            // validating the user input
	    while (b.getState(userMove)!=0) {
	    	System.out.println("Invalid move. Make your move again: ");
	    	userMove.x=b.scan.nextInt()-1;
	    	userMove.y=b.scan.nextInt()-1;
	    }

        // placing users move on the board
	    b.placeAMove(userMove, 2);
        // displaying updated board
            b.displayBoard();

            // checking if the game has ended after the users move
            if (b.isGameOver()) {
                break;
            } 

            // ai is making its move
            AI.callMinimax(0, 1, b, choice, choice);
            // displaying ai's move choices with scores
            for (PointsAndScores pas : AI.rootsChildrenScores) {
                System.out.println("Point: " + pas.point + " Score: " + pas.score);
            }
            // ai places the best move on the board
            b.placeAMove(AI.returnBestMove(), 1);
            // displaying updated board
            b.displayBoard();
        }
        // determining and announcing the winner
        if (b.hasXWon()) {
            System.out.println("Unfortunately, you lost!");
        } else if (b.hasOWon()) {
            System.out.println("You win!");
        } else {
            System.out.println("It's a draw!");
        }
    }    
}