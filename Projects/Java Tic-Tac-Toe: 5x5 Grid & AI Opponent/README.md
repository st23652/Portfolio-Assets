# Extended 5x5 Tic-Tac-Toe Game ⭕❌

## Overview
This project is a Java console application that scales the traditional Tic-Tac-Toe game into a more complex 5x5 grid environment. Rather than the standard 3-in-a-row rule, players must strategically place 4 consecutive marks (horizontally, vertically, or diagonally) to secure a victory against an AI opponent. The application features a persistent game loop, allowing for multiple rounds and continuous score tracking.

## Key Features
* **Expanded Grid & Custom Rules:** Modifies the classic game logic to operate on a 5x5 2D array (`char[][] board`), requiring a customized algorithm to detect 4-in-a-row win conditions.
* **Variable AI Difficulty:** Features an AI opponent with selectable "Easy" and "Medium" difficulty tiers, offering varied gameplay challenges.
* **Dynamic Scoring System:** Implements a persistent scoring tracker across game sessions. Winning on "Easy" rewards the player with 50 points, while conquering the "Medium" AI grants 100 points, encouraging replayability.
* **Robust Win Detection:** Utilizes complex nested loops to mathematically scan the board for horizontal, vertical, and both positive/negative diagonal victory states after every move.

## Project Architecture
The game is encapsulated within a single, highly structured `TicTacToe.java` class:
* **Initialization & Game Loop:** Manages board resets, user input parsing via `Scanner`, and turn-switching within a `do-while` loop.
* **Validation Logic:** Ensures user moves are within the 5x5 boundaries and prevents overwriting existing marks.
* **Terminal State Checks:** Dedicated boolean methods (`isBoardFull`, along with win-condition checkers) monitor the board to declare wins, losses, or draws accurately.

## Concepts Demonstrated
* Multidimensional Arrays (2D Grid Management)
* Complex Matrix Traversal Algorithms
* Game Loop and State Management
* Console I/O and Data Validation