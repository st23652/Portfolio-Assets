# AI Tic-Tac-Toe: Minimax & Alpha-Beta Pruning ⭕❌

## Overview
This project is a Java-based console application of the classic Tic-Tac-Toe game. It features a robust Artificial Intelligence engine that plays against the user. By implementing the Minimax algorithm enhanced with Alpha-Beta pruning, the AI opponent evaluates all possible future board states to ensure it never loses a game, always playing the most mathematically optimal move.

## Key Features
* **Unbeatable AI Opponent:** The computer analyzes the entire game tree using the Minimax algorithm to predict user responses and secure either a win or a draw.
* **Alpha-Beta Pruning Optimization:** The search algorithm is highly optimized. By maintaining `alpha` and `beta` thresholds, the AI trims branches of the decision tree that are guaranteed to be worse than previously evaluated moves, significantly reducing computation time.
* **Interactive Console Gameplay:** The system features a continuous game loop that displays a dynamic 3x3 grid, captures zero-based user coordinates safely, and validates moves to prevent overwriting existing plays.
* **Real-time AI Decision Logging:** The console prints out the underlying point scores for the AI's available moves, providing transparency into the algorithm's decision-making process.

## Project Architecture
The project is built using a clean Object-Oriented design separated into three core components:
* `Board.java`: The state manager. It defines the 3x3 matrix, tracks empty slots, checks for terminal game conditions (win/loss/draw), and handles the visual console output.
* `AIplayer.java`: The intelligence engine. It contains the recursive Minimax logic, state scoring metrics, and the Alpha-Beta pruning mechanisms used to return the optimal `Point` coordinate.
* `TicTacToe.java`: The main execution script and game loop. It handles user input parsing, turn-switching logic, and the final state announcements.

## Concepts Demonstrated
* Artificial Intelligence (Adversarial Search)
* Game Theory (Minimax Algorithm)
* Algorithm Optimization (Alpha-Beta Pruning)
* Java Object-Oriented Programming & Data Structures